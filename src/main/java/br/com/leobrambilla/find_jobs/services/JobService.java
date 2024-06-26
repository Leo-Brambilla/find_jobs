package br.com.leobrambilla.find_jobs.services;

import br.com.leobrambilla.find_jobs.entities.Job;
import br.com.leobrambilla.find_jobs.repositories.JobRepository;
import br.com.leobrambilla.find_jobs.specifications.JobSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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

    public List<Job> findByCriteria(Job.JobType jobType, Job.JobStatus jobStatus, String jobLocation, String jobRequisitions) {
        Specification<Job> spec = Specification.where(null);

        if (jobType != null) {
            spec = spec.and(JobSpecifications.hasJobType(jobType));
        }
        if (jobStatus != null) {
            spec = spec.and(JobSpecifications.hasJobStatus(jobStatus));
        }
        if (jobLocation != null && !jobLocation.isEmpty()) {
            spec = spec.and(JobSpecifications.hasJobLocation(jobLocation));
        }
        if (jobRequisitions != null && !jobRequisitions.isEmpty()) {
            spec = spec.and(JobSpecifications.hasJobRequisitions(jobRequisitions));
        }

        return jobRepository.findAll(spec);
    }

    public Job findById(UUID id) {

        return jobRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
    }

    public Job update(UUID id, Job updatedJob) {
        return jobRepository.findById(id).map(job -> {
            job.setJobTitle(updatedJob.getJobTitle());
            job.setNumberOfJobs(updatedJob.getNumberOfJobs());
            job.setPublicationDate(updatedJob.getPublicationDate());
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
