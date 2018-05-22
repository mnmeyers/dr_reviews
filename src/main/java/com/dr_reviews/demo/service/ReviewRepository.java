package com.dr_reviews.demo.service;

import com.dr_reviews.demo.domain.Review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ReviewRepository {
    Review mapRow(ResultSet resultSet, int rowNum) throws SQLException;
    Review findById(int id);
    List<Review> findReviewsByDoctorId(Integer doctorId);
    Review updateOrCreateReview(Review review);
    void deleteReview(Integer id);
}
