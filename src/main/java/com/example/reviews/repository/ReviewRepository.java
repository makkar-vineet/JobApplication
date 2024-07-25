package com.example.reviews.repository;

import com.example.reviews.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByCompanyId(Long id);
}
