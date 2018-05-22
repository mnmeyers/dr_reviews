package com.dr_reviews.demo.service;

import com.dr_reviews.demo.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReviewH2Repository implements ReviewRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String DOCTOR_ID_COLUMN = "doctor_id";//TODO make these an enum
    private static final String DOCTOR_NAME_COLUMN = "doctor_name";
    private static final String COMMENT_BODY_COLUMN = "comment_body";
    private static final String AUTHOR_ID_COLUMN = "author_id";
    private static final String AUTHOR_USERNAME_COLUMN = "author_username";
    private static final String REVIEW_ID_COLUMN = "review_id";
    private static final String IS_ACTIVE_COLUMN = "is_active";
    private static final String RATING_COLUMN = "rating";
    private static final String CREATED_AT_COLUMN = "created_at";

    class ReviewRowMapper implements RowMapper<Review> {
        @Override
        public Review mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return setReviewProperties(resultSet);
        }

    }

    @Override
    public Review mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return setReviewProperties(resultSet);

    }

    private static Review setReviewProperties(ResultSet resultSet) throws SQLException{
        Review review = new Review();
        review.setDoctor_id(resultSet.getInt(DOCTOR_ID_COLUMN));
        review.setDoctor_name(resultSet.getString(DOCTOR_NAME_COLUMN));
        review.setReview_id(resultSet.getInt(REVIEW_ID_COLUMN));
        review.setAuthor_username(resultSet.getString(AUTHOR_USERNAME_COLUMN));
        review.setAuthor_id(resultSet.getInt(AUTHOR_ID_COLUMN));
        review.setComment_body(resultSet.getString(COMMENT_BODY_COLUMN));
        review.setRating(resultSet.getInt(RATING_COLUMN));
        review.setCreated_at(resultSet.getDate(CREATED_AT_COLUMN));
        review.setIs_active(resultSet.getBoolean(IS_ACTIVE_COLUMN));
        return review;
    }

    @Override
    public Review findById(int id) {

        String sql = "SELECT review_id, doctors.doctor_id, author_id, comment_body, rating, created_at, is_active,  " +
                "doctors.name AS doctor_name, users.username AS author_username  " +
                "FROM reviews " +
                "LEFT JOIN doctors " +
                "ON reviews.doctor_id = doctors.doctor_id " +
                "LEFT JOIN users " +
                "ON reviews.author_id = users.user_id " +
                "where review_id=?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id },
                new BeanPropertyRowMapper<>(Review.class));
    }

    @Override
    public List<Review> findReviewsByDoctorId(Integer doctorId) {
        String sql = "SELECT review_id, doctors.doctor_id, author_id, comment_body, rating, created_at, is_active,  " +
                "doctors.name AS doctor_name, users.username AS author_username  " +
                "FROM reviews " +
                "LEFT JOIN doctors " +
                "ON reviews.doctor_id = doctors.doctor_id " +
                "LEFT JOIN users " +
                "ON reviews.author_id = users.user_id " +
                "where doctors.doctor_id=?";

        return jdbcTemplate.query(sql, new Object[] {doctorId}, new ReviewRowMapper());

    }

    @Override
    public Review updateOrCreateReview(Review review) {
        String sql = "MERGE INTO reviews (review_id, doctor_id, author_id, comment_body, rating, created_at, is_active) KEY(review_id) VALUES(?,?,?,?,?,?,?)";

        jdbcTemplate.update(sql,
                review.getReview_id(),
                review.getDoctor_id(),
                review.getAuthor_id(),
                review.getComment_body(),
                review.getRating(),
                review.getCreated_at(),
                review.getIs_active());

        return review;
    }


    @Override
    public void deleteReview(Integer id) {
        String sql = "DELETE FROM reviews WHERE review_id=?";
        jdbcTemplate.update(sql, id);
    }
}
