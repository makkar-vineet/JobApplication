package com.example.reviews.abstractService;

import com.example.firstJobApp.jobEntity.Job;
import com.example.reviews.entity.Review;

import java.util.List;

public interface ReviewService {
    boolean deleteJobByID(Long reviewId ,Long id);

    List<Review> getById(Long id);

    Integer saveReview(Long id ,Review review);

    Review getByCompanyAndReviwId(Long companyId, Long revId);

    Review updateReview(Review rev,Long compId);
}
