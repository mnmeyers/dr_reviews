package com.dr_reviews.demo.service;

import com.dr_reviews.demo.domain.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;//fix this

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DoctorController {
    @Autowired
    DoctorRepository doctorRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/doctors")
    public List<Doctor> getDoctorsBySpecialtyAndCity(
            @RequestParam String specialtyName,
            @RequestParam String cityName,
            @RequestParam String state) {
        return doctorRepository.findDoctorsBySpecialtyAndCity(specialtyName, cityName, state);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/doctors/{docId}")
    public List<Doctor> getDoctorsById(@PathVariable Integer docId) {
        return new ArrayList<>();
    }
}
