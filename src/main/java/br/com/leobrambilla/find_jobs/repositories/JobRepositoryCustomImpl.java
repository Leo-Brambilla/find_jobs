package br.com.leobrambilla.find_jobs.repositories;

import br.com.leobrambilla.find_jobs.entities.Job;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class JobRepositoryCustomImpl implements JobRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Job> findAll(Specification<Job> spec) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Job> cq = cb.createQuery(Job.class);
        Root<Job> job = cq.from(Job.class);

        Predicate[] predicates = new Predicate[]{spec.toPredicate(job, cq, cb)};
        cq.where(predicates);

        return entityManager.createQuery(cq).getResultList();
    }
}
