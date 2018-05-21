package com.dr_reviews.demo;

import com.dr_reviews.demo.config.DrReviewsConfig;
import com.dr_reviews.demo.service.DoctorRepository;
import com.dr_reviews.demo.service.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration //(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
@ComponentScan(basePackages={"com.dr_reviews.demo"})
public class DemoApplication implements CommandLineRunner {

    @Autowired
    DoctorRepository drRepo;

    @Autowired
    ReviewRepository reviewRepo;

    public static void main(String[] args) {

//        final Class[] sources =
//                {
//                        DrReviewsConfig.class,
//                };
//
//        SpringApplication.run(sources, args);
        SpringApplication.run(DemoApplication.class, args);


    }

    @Override
    public void run(String... args) throws Exception {
//        drRepo.createTables();
//        reviewRepo.createTables();
    }
}
