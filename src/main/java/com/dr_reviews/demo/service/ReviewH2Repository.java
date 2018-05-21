package com.dr_reviews.demo.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewH2Repository implements ReviewRepository {
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void createTables() {
        String sql = "CREATE TABLE users(" +
                "user_id IDENTITY NOT NULL," +
                "name VARCHAR," +
                "username VARCHAR," +
                "PRIMARY KEY (user_id)" +
                ");" +
                "CREATE TABLE reviews(" +
                "review_id IDENTITY NOT NULL," +
                "comment_body VARCHAR," +
                "doctor_id INT," +
                "FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id)," +
                "created_at TIMESTAMP," +
                "rating TINYINT," +
                "author_id INT," +
                "is_active BOOLEAN," +
                "FOREIGN KEY (author_id) REFERENCES users(user_id)," +
                "PRIMARY KEY (review_id)" +
                ");";
        jdbcTemplate.execute(sql);//TODO catch and log error

    }
}
