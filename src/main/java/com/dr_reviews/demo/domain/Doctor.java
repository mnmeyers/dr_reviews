package com.dr_reviews.demo.domain;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Data @Entity
public class Doctor {
    @Id @GeneratedValue
    private Integer doctor_id;
    private String name;
    private Integer specialty_id;
    private String specialty;
    private Integer city_id;
    private String city;
    private String address;
}
