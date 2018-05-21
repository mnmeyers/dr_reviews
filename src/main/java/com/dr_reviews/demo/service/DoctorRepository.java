package com.dr_reviews.demo.service;

import com.dr_reviews.demo.domain.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DoctorRepository {
    void createTables();
    Doctor mapRow(ResultSet resultSet, int rowNum) throws SQLException;
    Doctor findById(int id);
    List<Doctor> findDoctorsBySpecialtyAndCity(String specialtyName, String cityName, String state);
}
