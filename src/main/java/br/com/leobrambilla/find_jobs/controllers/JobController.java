package br.com.leobrambilla.find_jobs.controllers;

import br.com.leobrambilla.find_jobs.entities.Job;
import br.com.leobrambilla.find_jobs.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobService.save(job);
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.findAll();
    }

    @GetMapping("/{id}")
    public Job findJobById(@PathVariable UUID id) {
        return jobService.findById(id);
    }

    @PutMapping("/{id}")
    public Job updateJob(@PathVariable UUID id, @RequestBody Job updatedJob) {
        return jobService.update(id, updatedJob);
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable UUID id) {
        jobService.delete(id);
    }
}
