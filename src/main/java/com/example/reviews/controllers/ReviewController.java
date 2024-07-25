package com.example.reviews.controllers;

import com.example.firstJobApp.jobEntity.Job;
import com.example.reviews.abstractService.ReviewService;
import com.example.reviews.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class ReviewController {

    @Autowired
   private ReviewService reviewService;



//    @GetMapping("/jobs")
//    public List<Review> findAll(){
//        return this.reviewService.findAll();
//        // return lq;

//    }

    @PostMapping("/{companyId}/review")
    public ResponseEntity<String> createReview(@PathVariable Long companyId,@RequestBody Review review) {
        Integer returnCode = this.reviewService.saveReview(companyId,review);
        if (returnCode.equals(1))
            return new ResponseEntity<>("success", HttpStatus.OK);
        return new ResponseEntity<>("failed", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/review/{companyId}")
    public ResponseEntity<List<Review>> getReviewById(@PathVariable Long companyId) {
        List<Review> j = this.reviewService.getById(companyId);
        if (j != null) {
            return new ResponseEntity<>(j, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}/deleteReviewByID/{reviewId}")
    public ResponseEntity<String> deleteById(@PathVariable Long reviewId , @PathVariable Long id) {
        boolean result = reviewService.deleteJobByID(reviewId,id);
        if (result)
            return ResponseEntity.ok("Delete Successfully");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{companyId}/review/{revId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId ,@PathVariable Long revId) {
       Review j = this.reviewService.getByCompanyAndReviwId(companyId,revId);
        if (j != null) {
            return new ResponseEntity<>(j, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{companyId}/updateReview")
    public ResponseEntity<Review> updateRev(@RequestBody Review rev,@PathVariable Long companyId){
        Review updateRev = reviewService.updateReview(rev,companyId);
        if(updateRev != null)
            return new ResponseEntity<>(updateRev,HttpStatus.OK);
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }


}
