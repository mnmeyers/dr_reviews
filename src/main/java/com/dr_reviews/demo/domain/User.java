package com.dr_reviews.demo.domain;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Data @Entity
public class User {
    @Id @GeneratedValue
    private Integer user_id;
    private String name;
    private String username;
}
