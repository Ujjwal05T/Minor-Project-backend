package com.ujjwal.authProject.repo;

import com.ujjwal.authProject.model.JobDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobDetailsRepo extends JpaRepository <JobDetails,String> {
}
