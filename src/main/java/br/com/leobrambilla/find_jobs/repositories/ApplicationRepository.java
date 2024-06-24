package br.com.leobrambilla.find_jobs.repositories;

import br.com.leobrambilla.find_jobs.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, UUID> {
    List<Application> findByUserId(UUID userId);
    List<Application> findByJobId(UUID jobId);
}
