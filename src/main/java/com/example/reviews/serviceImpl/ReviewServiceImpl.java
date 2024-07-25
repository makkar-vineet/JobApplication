package com.example.reviews.serviceImpl;

import com.example.company.AbstractService.CompanyService;
import com.example.company.entity.Company;
import com.example.firstJobApp.jobEntity.Job;
import com.example.reviews.abstractService.ReviewService;
import com.example.reviews.entity.Review;
import com.example.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private CompanyService companyService;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public boolean deleteJobByID(Long rev, Long id) {
       if( companyService.getByCompanyId(id) !=null && reviewRepository.existsById(rev)){
           Review review = reviewRepository.findById(id).orElse(null);
           Company company = review.getCompany();
           company.getReviews().remove(review);
           companyService.updateJob(rev,company);
           return true;
       }
        return false;
    }

    @Override
    public List<Review> getById(Long companyId) {
        List<Review> allReview = reviewRepository.findByCompanyId(companyId);

        return allReview;
    }

    @Override
    public Integer saveReview(Long id, Review review) {
        Review rw = new Review();
        Company comp = companyService.getByCompanyId(id);
        if (comp.getId() > 0) {
            review.setCompany(comp);
            rw = reviewRepository.save(review);

        }
        if (rw.getId() != null) {
            return 1;
        }
        return 0;
    }

    @Override
    public Review getByCompanyAndReviwId(Long companyId, Long revId) {
        List<Review> allRev = reviewRepository.findByCompanyId(companyId);
        return allRev.stream().filter(i -> i.getId().equals(revId)).findFirst().orElse(null);
    }

    @Override
    public Review updateReview(Review rev ,Long compId) {
        if (companyService.getByCompanyId(compId) !=null) {
           // Review revow = reviewRepository.findById(rev.getId()).orElse(null);
            rev.setCompany(companyService.getByCompanyId(compId));
            rev.setId(rev.getId());
            return reviewRepository.save(rev);
        }

        return null;
    }
}
