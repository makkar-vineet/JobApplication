package com.example.firstJobApp.Controllers;

import com.example.firstJobApp.AbstractService.JobService;
import com.example.firstJobApp.jobEntity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {

   @Autowired
   private JobService jobService;

    @GetMapping("/jobs")
    public List<Job> findAll(){
        return this.jobService.findAll();
   // return lq;
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createjob(@RequestBody Job job){
       this.jobService.createJob(job);
        return new ResponseEntity<>("success",HttpStatus.OK);
    }

    @GetMapping("/jobs/{id}")
        public ResponseEntity<Job> getJobById(@PathVariable("id") Long id){
        Job j = this.jobService.getById(id);
        if(j!=null){
            return new ResponseEntity<>(j, HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deleteByID/{id}")
    public  ResponseEntity<String> deleteById(@PathVariable Long id){
        boolean result=  jobService.deleteJobByID(id);
        if(result)
        return ResponseEntity.ok("Delete Successfully");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateJob")
    public ResponseEntity<Job> updateJob(@RequestBody Job job){
        Job updateJobb = jobService.updateJob(job.getId(),job);
        if(updateJobb != null)
            return new ResponseEntity<>(updateJobb,HttpStatus.OK);
        return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
