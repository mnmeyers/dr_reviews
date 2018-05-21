package com.dr_reviews.demo.config;

import com.dr_reviews.demo.service.DoctorH2Repository;
import com.dr_reviews.demo.service.DoctorRepository;
import com.dr_reviews.demo.service.ReviewH2Repository;
import com.dr_reviews.demo.service.ReviewRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by mmeyers on 5/5/18.
 */

@Configuration
@EnableWebMvc
public class DrReviewsConfig {

//    @Bean
//    public DoctorRepository doctorRepository() {
//        return new DoctorH2Repository();
//    }
//
//    @Bean
//    public ReviewRepository reviewRepository() {
//        return new ReviewH2Repository();
//    }

}
