package com.example.demo.service.impl;

import com.example.demo.entity.Job;
import com.example.demo.repository.JobRepository;
import com.example.demo.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceimpl implements JobService {
    private final JobRepository jobRepository;

    public JobServiceimpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }


    @Override
    public Job getJob(Long id) {
        Optional<Job> find_job = jobRepository.findById(id);
        return find_job.orElse(null);
    }

    @Override
    public Job updateJob(Long id, Job job) {
        Optional<Job> existing_job = jobRepository.findById(id);
        if (existing_job.isPresent()) {
            Job existingJob = existing_job.get();
            existingJob.setDescription(job.getDescription());
            existingJob.setLocation(job.getLocation());
            existingJob.setTitle(job.getTitle());
            existingJob.setMaxSalary(job.getMaxSalary());
            existingJob.setMinSalary(job.getMinSalary());

            return jobRepository.save(existingJob);
        }
        return null;
    }

    @Override
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
