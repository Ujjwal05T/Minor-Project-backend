package com.ujjwal.authProject.service;

import com.ujjwal.authProject.model.JobDetails;
import com.ujjwal.authProject.repo.JobDetailsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobDetailsRepo repo;

    public JobService(JobDetailsRepo repo) {
        this.repo = repo;
    }

    public List<JobDetails> getAllJobs() {
        return repo.findAll();
    }

    public JobDetails addJob(JobDetails jobDetails) {
        return repo.save(jobDetails);
    }
}
