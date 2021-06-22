package ar.edu.unq.desapp.grupoD.backenddesapptp.persistence;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.Review;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.ReviewPage;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.ReviewSearchCriteria;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.ReviewType;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ReviewCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public ReviewCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<ReviewType> findAllWithFilters(ReviewPage reviewPage,
                                           ReviewSearchCriteria reviewSearchCriteria){
        CriteriaQuery<ReviewType> criteriaQuery = criteriaBuilder.createQuery(ReviewType.class);
        Root<ReviewType> reviewTypeRoot = criteriaQuery.from(ReviewType.class);

        Predicate predicate = getPredicate(reviewSearchCriteria, reviewTypeRoot);
        criteriaQuery.where(predicate);
        setOrder(reviewPage, criteriaQuery, reviewTypeRoot);

        TypedQuery<ReviewType> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(reviewPage.getPageNumber() * reviewPage.getPageSize());
        typedQuery.setMaxResults(reviewPage.getPageSize());

        Pageable pageable = getPageable(reviewPage);

        long reviewsCount = getReviewsCount(predicate);


        return new PageImpl<>(typedQuery.getResultList(), pageable, reviewsCount);
    }

    private Predicate getPredicate(ReviewSearchCriteria reviewSearchCriteria,
                                   Root<ReviewType> reviewTypeRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(reviewSearchCriteria.getCity())){
            predicates.add(
                    criteriaBuilder.like(reviewTypeRoot.get("city"),
                            "%" + reviewSearchCriteria.getCity() + "%")
            );
        }
        if(Objects.nonNull(reviewSearchCriteria.getPlatform())){
            predicates.add(
                    criteriaBuilder.like(reviewTypeRoot.get("platform"),
                            "%" + reviewSearchCriteria.getPlatform() + "%")
            );
        }
        if(Objects.nonNull(reviewSearchCriteria.getLanguage())){
            predicates.add(
                    criteriaBuilder.like(reviewTypeRoot.get("languaje"),
                            "%" + reviewSearchCriteria.getLanguage() + "%")
            );
        }
        if(Objects.nonNull(reviewSearchCriteria.isSpoilerAlert())){
            predicates.add(criteriaBuilder.equal(reviewTypeRoot.get("spoilerAlert"), reviewSearchCriteria.isSpoilerAlert()));
        }
        if(Objects.nonNull(reviewSearchCriteria.isPremium())){
            predicates.add(criteriaBuilder.equal(reviewTypeRoot.get("isPremium"), reviewSearchCriteria.isPremium()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(ReviewPage reviewPage,
                          CriteriaQuery<ReviewType> criteriaQuery,
                          Root<ReviewType> reviewTypeRoot) {
        if(reviewPage.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(reviewTypeRoot.get(reviewPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(reviewTypeRoot.get(reviewPage.getSortBy())));
        }
    }

    private Pageable getPageable(ReviewPage reviewPage) {
        Sort sort = Sort.by(reviewPage.getSortDirection(), reviewPage.getSortBy());
        return PageRequest.of(reviewPage.getPageNumber(), reviewPage.getPageSize(), sort);
    }

    private long getReviewsCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<ReviewType> countRoot = countQuery.from(ReviewType.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
