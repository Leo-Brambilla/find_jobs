package br.com.leobrambilla.find_jobs.repositories;

import br.com.leobrambilla.find_jobs.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface JobRepository extends JpaRepository<Job, UUID>, JobRepositoryCustom, JpaSpecificationExecutor<Job> {
}
