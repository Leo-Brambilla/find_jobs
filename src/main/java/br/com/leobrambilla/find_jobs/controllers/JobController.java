package br.com.leobrambilla.find_jobs.controllers;

import br.com.leobrambilla.find_jobs.entities.Job;
import br.com.leobrambilla.find_jobs.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.findAll();
    }

    @GetMapping("/search")
    public List<Job> searchJobs(
            @RequestParam(required = false) Job.JobType jobType,
            @RequestParam(required = false) Job.JobStatus jobStatus,
            @RequestParam(required = false) String jobLocation,
            @RequestParam(required = false) String jobRequisitions) {
        return jobService.findByCriteria(jobType, jobStatus, jobLocation, jobRequisitions);
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable UUID id) {
        return jobService.findById(id);
    }


    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        Job createdJob = jobService.save(job);
        return ResponseEntity.ok(createdJob);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Job updateJob(@PathVariable UUID id, @RequestBody Job job) {
        return jobService.update(id, job);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable UUID id) {
        jobService.delete(id);
    }
}
