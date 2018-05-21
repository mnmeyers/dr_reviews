package com.dr_reviews.demo.domain;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.util.Date;

@Data
@Entity
public class Review {
    @Id @GeneratedValue
    private Integer review_id;
    private Integer doctor_id;
    private Integer author_id;
    private String comment_body;
    private Integer rating;
    private Boolean is_active;
    private Date created_at;
}
