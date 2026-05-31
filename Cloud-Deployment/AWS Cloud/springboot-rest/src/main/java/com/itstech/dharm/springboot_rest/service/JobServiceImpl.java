package com.itstech.dharm.springboot_rest.service;

import com.itstech.dharm.springboot_rest.model.JobPost;
import com.itstech.dharm.springboot_rest.dao.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private JobRepository  jobRepository;

    @Autowired
    public void setJobRepository(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public void addJob(JobPost job) {
        jobRepository.save(job);
    }

    @Override
    public JobPost getJobByJobPostId(int postId) {
        return jobRepository.findById(postId).orElse(new JobPost());
    }

    @Override
    public List<JobPost> getAllJobs() {

        return jobRepository.findAll();
    }

    @Override
    public void updateJob(JobPost job) {

        jobRepository.save(job);
    }

    @Override
    public void deleteJob(int postId) {
        jobRepository.deleteById(postId);
    }

    @Override
    public void load() {

        // ArrayList to store JobPost objects
        List<JobPost> jobs = new ArrayList<>(Arrays.asList(

                new JobPost(1, "Java Developer", "Must have good experience in core Java and advanced Java", 2,
                        List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")),


                new JobPost(2, "Frontend Developer", "Experience in building responsive web applications using React", 3,
                        List.of("HTML", "CSS", "JavaScript", "React")),


                new JobPost(3, "Data Scientist", "Strong background in machine learning and data analysis", 4,
                        List.of("Python", "Machine Learning", "Data Analysis")),


                new JobPost(4, "Network Engineer", "Design and implement computer networks for efficient data communication", 5,
                        List.of("Networking", "Cisco", "Routing", "Switching")),


                new JobPost(5, "Mobile App Developer", "Experience in mobile app development for iOS and Android", 3,
                        List.of("iOS Development", "Android Development", "Mobile App"))
        ));

        jobRepository.saveAll(jobs);
    }

    @Override
    public List<JobPost> searchJob(String keyword) {
        return jobRepository.findByPostProfileContainingOrPostDescContaining(keyword, keyword);
    }
}
