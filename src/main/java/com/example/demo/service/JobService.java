package com.example.demo.service;

import com.example.demo.entity.Job;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JobService {
    ResponseEntity<Job> createJob(Job job);
    ResponseEntity<List<Job>> getAllJobs();
    ResponseEntity<Job> getJob(Long id);
    ResponseEntity<Job> updateJob(Long id, Job job);
    ResponseEntity<String> deleteJob(Long id);
}
