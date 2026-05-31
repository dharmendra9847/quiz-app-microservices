package com.itstech.dharm.springboot_rest.service;

import com.itstech.dharm.springboot_rest.model.JobPost;

import java.util.List;

public interface JobService {

    void addJob(JobPost job);
    JobPost getJobByJobPostId(int postId);
    List<JobPost> getAllJobs();
    void updateJob(JobPost job);

    void deleteJob(int postId);

    void load();

    List<JobPost> searchJob(String keyword);
}
