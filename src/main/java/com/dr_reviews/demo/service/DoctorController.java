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

    /**
     * For recommendations to show users when they view another review of a doctor with the passed
     * in city, state, and specialty. State is just to avoid getting the wrong city's results (i.e. Springfield is a city in many different states)
     * @param specialtyName
     * @param cityName
     * @param state
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/doctors")
    public List<Doctor> getDoctorsBySpecialtyAndCity(
            @RequestParam String specialtyName,
            @RequestParam String cityName,
            @RequestParam String state) {
        return doctorRepository.findDoctorsBySpecialtyAndCity(specialtyName, cityName, state);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/doctors/{docId}")
    public Doctor getDoctorById(@PathVariable Integer docId) {
        return doctorRepository.findById(docId);
    }
}
