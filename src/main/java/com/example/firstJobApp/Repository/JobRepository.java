package com.example.firstJobApp.Repository;

import com.example.firstJobApp.jobEntity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
}