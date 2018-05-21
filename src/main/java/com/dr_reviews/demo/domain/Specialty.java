package com.dr_reviews.demo.domain;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Data @Entity
public class Specialty {
    @Id @GeneratedValue
    private Integer specialty_id;
    private String name;
}
