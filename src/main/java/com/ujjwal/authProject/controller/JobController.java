package com.ujjwal.authProject.controller;

import com.ujjwal.authProject.model.JobDetails;
import com.ujjwal.authProject.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class JobController {

    @Autowired
    private JobService jobService;

    @PreAuthorize("USER")
    @GetMapping("/jobs")
    public List<JobDetails> getJobs(){
        return jobService.getAllJobs();
    }
}
