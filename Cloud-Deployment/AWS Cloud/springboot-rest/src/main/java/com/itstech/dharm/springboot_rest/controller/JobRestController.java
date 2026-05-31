package com.itstech.dharm.springboot_rest.controller;

import com.itstech.dharm.springboot_rest.model.JobPost;
import com.itstech.dharm.springboot_rest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JobRestController {

    private JobService jobService;

    @Autowired
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("jobPost")
    public JobPost addJob(@RequestBody JobPost job) {
        jobService.addJob(job);
        return job;
    }

    @GetMapping("jobPost/{postId}")
    public JobPost getJobByPostId(@PathVariable  int postId) {
        return jobService.getJobByJobPostId(postId);
    }

    @GetMapping("jobPost/keyword/{keyword}")
    public List<JobPost> searchJobByKeyword(@PathVariable String keyword) {
        return jobService.searchJob(keyword);
    }

    @GetMapping("jobPosts")
    //@ResponseBody
    public List<JobPost> getAllJobs() {
        return jobService.getAllJobs();
    }

    @PutMapping("jobPost")
    public JobPost updateJob(@RequestBody JobPost job) {
        jobService.updateJob(job);
        return jobService.getJobByJobPostId(job.getPostId());
    }

    @DeleteMapping("jobPost/{postId}")
    public String deleteJobByPostId(@PathVariable int postId) {
        jobService.deleteJob(postId);
        return "data deleted successfully";
    }

    @GetMapping("load")
    public String loadAllJobs() {
        jobService.load();
        return "data loaded successfully";
    }
}