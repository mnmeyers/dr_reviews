package com.dr_reviews.demo.domain;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Data @Entity
public class City {
    @Id @GeneratedValue
    private Integer city_id;
    private String name;
    private String state;
}
