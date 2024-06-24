package br.com.leobrambilla.find_jobs.controllers;

import br.com.leobrambilla.find_jobs.entities.Application;
import br.com.leobrambilla.find_jobs.entities.Job;
import br.com.leobrambilla.find_jobs.entities.User;
import br.com.leobrambilla.find_jobs.services.ApplicationService;
import br.com.leobrambilla.find_jobs.services.JobService;
import br.com.leobrambilla.find_jobs.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;

    @PostMapping
    public Application applyToJob(@RequestBody Application application) {
        return applicationService.save(application);
    }

    @GetMapping("/user/{userId}")
    public List<Application> getUserApplications(@PathVariable UUID userId) {
        return applicationService.findByUserId(userId);
    }

    @GetMapping("/job/{jobId}")
    public List<Application> getJobApplications(@PathVariable UUID jobId) {
        return applicationService.findByJobId(jobId);
    }
}
