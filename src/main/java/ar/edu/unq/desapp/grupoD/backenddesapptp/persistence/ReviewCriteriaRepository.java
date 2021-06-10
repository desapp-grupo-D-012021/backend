package ar.edu.unq.desapp.grupoD.backenddesapptp.persistence;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.ReviewPage;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.ReviewSearchCriteria;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.ReviewType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ReviewCriteriaRepository {

    private EntityManager entityManager;

    private CriteriaBuilder criteriaBuilder;

    public ReviewCriteriaRepository(EntityManager entityManager){
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<ReviewType> findAllWithFilters(ReviewPage reviewPage, ReviewSearchCriteria reviewSearchCriteria){
        CriteriaQuery<ReviewType> criteriaQuery = criteriaBuilder.createQuery(ReviewType.class);
        Root<ReviewType> reviewRoot = criteriaQuery.from(ReviewType.class);
        Predicate predicate = getPredicate(reviewSearchCriteria, reviewRoot);
        criteriaQuery.where(predicate);
        setOrder(reviewPage, criteriaQuery, reviewRoot);

        TypedQuery<ReviewType> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(reviewPage.getPageNumber() * reviewPage.getPageSize());
        typedQuery.setMaxResults(reviewPage.getPageSize());

        Pageable pageable = getPageable(reviewPage);

        long reviewsCount = getReviewTypesCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, reviewsCount);
    }

    private Predicate getPredicate(ReviewSearchCriteria reviewSearchCriteria, Root<ReviewType> reviewRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(reviewSearchCriteria.getPlatform())){
            predicates.add(criteriaBuilder.like(reviewRoot.get("platform"), "%" + reviewSearchCriteria.getPlatform() + "%"));
        }
        if(Objects.nonNull(reviewSearchCriteria.getSpoilerAlert())){
            predicates.add(criteriaBuilder.like(reviewRoot.get("spoilerAlert"), "%" + reviewSearchCriteria.getSpoilerAlert() + "%"));
        }
        if(Objects.nonNull(reviewSearchCriteria.getIsPremium())){
            predicates.add(criteriaBuilder.like(reviewRoot.get("isPremium"), "%" + reviewSearchCriteria.getIsPremium() + "%"));
        }
        if(Objects.nonNull(reviewSearchCriteria.getLanguage())){
            predicates.add(criteriaBuilder.like(reviewRoot.get("language"), "%" + reviewSearchCriteria.getLanguage() + "%"));
        }
        if(Objects.nonNull(reviewSearchCriteria.getCity())){
            predicates.add(criteriaBuilder.like(reviewRoot.get("city"), "%" + reviewSearchCriteria.getCity() + "%"));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(ReviewPage reviewPage, CriteriaQuery<ReviewType> criteriaQuery, Root<ReviewType> reviewRoot) {
        if(reviewPage.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(reviewRoot.get(reviewPage.getSortBy())));
        }else{
            criteriaQuery.orderBy(criteriaBuilder.desc(reviewRoot.get(reviewPage.getSortBy())));
        }
    }

    private Pageable getPageable(ReviewPage reviewPage) {
        Sort sort = Sort.by(reviewPage.getSortDirection(),reviewPage.getSortBy());
        return PageRequest.of(reviewPage.getPageNumber(), reviewPage.getPageNumber(),sort);
    }

    private long getReviewTypesCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(long.class);
        Root<ReviewType> countRoot = countQuery.from(ReviewType.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

}
