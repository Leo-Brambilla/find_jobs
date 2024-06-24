package br.com.leobrambilla.find_jobs.services;

import br.com.leobrambilla.find_jobs.entities.Application;
import br.com.leobrambilla.find_jobs.repositories.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public Application save(Application application) {
        return applicationRepository.save(application);
    }

    public List<Application> findByUserId(UUID userId) {
        return applicationRepository.findByUserId(userId);
    }

    public List<Application> findByJobId(UUID jobId) {
        return applicationRepository.findByJobId(jobId);
    }
}
