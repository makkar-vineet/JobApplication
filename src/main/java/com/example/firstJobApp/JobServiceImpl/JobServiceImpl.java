package com.example.firstJobApp.JobServiceImpl;

import com.example.firstJobApp.AbstractService.JobService;
import com.example.firstJobApp.Repository.JobRepository;
import com.example.firstJobApp.jobEntity.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
//      List<Job> jobs = new ArrayList<>();

    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.saveAndFlush(job);
    }

    @Override
    public Job getById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobByID(Long id) {
        //boolean result = jobs.removeIf(i -> i.getId().equals(id));
        //  result= jobs.remove(getById(id));
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Job updateJob(Long id, Job job) {
        Optional<Job> jobOptional= jobRepository.findById(id);
            if (jobOptional.isPresent()) {
                Job j = jobOptional.get();
                j.setDescription(job.getDescription());
                j.setJobtitle(job.getJobtitle());
                j.setLocation(job.getLocation());
                j.setMaxSalary(job.getMaxSalary());
                j.setMinSalary(job.getMinSalary());
                jobRepository.save(j);
                return j;
            }
        return null;
    }
}