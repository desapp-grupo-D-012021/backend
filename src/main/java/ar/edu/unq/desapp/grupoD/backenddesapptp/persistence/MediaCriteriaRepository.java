package ar.edu.unq.desapp.grupoD.backenddesapptp.persistence;

import ar.edu.unq.desapp.grupoD.backenddesapptp.model.Media;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.MediaPage;
import ar.edu.unq.desapp.grupoD.backenddesapptp.model.MediaSearchCriteria;
import org.springframework.context.annotation.Configuration;
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

@Configuration
@Repository
public class MediaCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public MediaCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Media> findAllWithFilters(MediaPage mediaPage, MediaSearchCriteria mediaSearchCriteria) {
        CriteriaQuery<Media> criteriaQuery = criteriaBuilder.createQuery(Media.class);
        Root<Media> mediaRoot = criteriaQuery.from(Media.class);
        Predicate predicate = getPredicate(mediaSearchCriteria, mediaRoot);

        criteriaQuery.where(predicate);
        setOrder(mediaPage, criteriaQuery, mediaRoot);

        TypedQuery<Media> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(mediaPage.getPageNumber() * mediaPage.getPageSize());
        typedQuery.setMaxResults(mediaPage.getPageSize());

        Pageable pageable = getPageable(mediaPage);
        long mediaCount = getMediaCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, mediaCount);
    }

    private Predicate getPredicate(MediaSearchCriteria mediaSearchCriteria, Root<Media> mediaRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(mediaSearchCriteria.getTitle())){
            predicates.add(criteriaBuilder.like(mediaRoot.get("title"), "%" + mediaSearchCriteria.getTitle() + "%"));
        }
        if(Objects.nonNull(mediaSearchCriteria.getGenre())){
            predicates.add(criteriaBuilder.like(mediaRoot.get("genre"), "%" + mediaSearchCriteria.getGenre() + "%"));
        }
        if(Objects.nonNull(mediaSearchCriteria.getYear())){
            predicates.add(criteriaBuilder.gt(mediaRoot.get("year"), mediaSearchCriteria.getYear()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(MediaPage mediaPage, CriteriaQuery<Media> criteriaQuery, Root<Media> mediaRoot) {
        if(mediaPage.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(mediaRoot.get(mediaPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(mediaRoot.get(mediaPage.getSortBy())));
        }
    }

    private Pageable getPageable(MediaPage mediaPage) {
        Sort sort = Sort.by(mediaPage.getSortDirection(), mediaPage.getSortBy());
        return PageRequest.of(mediaPage.getPageNumber(), mediaPage.getPageSize(), sort);
    }

    private long getMediaCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Media> countRoot = countQuery.from(Media.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
