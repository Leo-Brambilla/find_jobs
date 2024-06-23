package br.com.leobrambilla.find_jobs.repositories;

import br.com.leobrambilla.find_jobs.entities.Job;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface JobRepositoryCustom {
    List<Job> findAll(Specification<Job> spec);
}
