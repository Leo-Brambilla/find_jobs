package br.com.leobrambilla.find_jobs.services;

import br.com.leobrambilla.find_jobs.entities.Job;
import br.com.leobrambilla.find_jobs.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public Job save(Job job) {
        return jobRepository.save(job);
    }

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public Job findById(UUID id) {
        return jobRepository.findById(id).orElse(null);
    }

    public Job update(UUID id, Job updatedJob) {
        return jobRepository.findById(id).map(job -> {
            job.setJobTitle(updatedJob.getJobTitle());
            job.setNumberOfJobs(updatedJob.getNumberOfJobs());
            job.setPublicationDate(updatedJob.getPublicationDate());
            job.setPayment(updatedJob.getPayment());
            job.setCompany(updatedJob.getCompany());
            job.setJobDescription(updatedJob.getJobDescription());
            job.setJobStatus(updatedJob.getJobStatus());
            job.setJobType(updatedJob.getJobType());
            job.setJobLocation(updatedJob.getJobLocation());
            job.setJobRequisitions(updatedJob.getJobRequisitions());
            return jobRepository.save(job);
        }).orElse(null);
    }

    public void delete(UUID id) {
        jobRepository.deleteById(id);
    }
}
