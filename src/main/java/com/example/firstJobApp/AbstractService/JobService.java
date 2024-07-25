package com.example.firstJobApp.AbstractService;

import com.example.firstJobApp.jobEntity.Job;

import java.util.List;

public interface JobService {

    List<Job> findAll();

    void createJob(Job job);

    Job getById(Long id);

    boolean deleteJobByID(Long id);

    Job updateJob(Long id, Job job);
}
