package com.dr_reviews.demo.service;

import com.dr_reviews.demo.domain.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DoctorH2Repository implements DoctorRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final String DOCTOR_ID_COLUMN = "doctor_id";//TODO make these an enum
    private static final String SPECIALTY_ID_COLUMN = "specialty_id";
    private static final String SPECIALTY_COLUMN = "specialty";
    private static final String CITY_ID_COLUMN = "city_id";
    private static final String CITY_COLUMN = "city";
    private static final String NAME_COLUMN = "name";
    private static final String ADDRESS_COLUMN = "address";


    @Override
    public void createTables() {
        String sql = "CREATE TABLE specialties(" +
                "specialty_id IDENTITY NOT NULL," +
                "name VARCHAR_IGNORECASE," +
                "PRIMARY KEY(specialty_id)" +
                ");" +
                "CREATE TABLE cities(" +
                "city_id IDENTITY NOT NULL," +
                "name VARCHAR_IGNORECASE," +
                "state VARCHAR_IGNORECASE," +
                "PRIMARY KEY(city_id)" +
                ");" +
                "CREATE TABLE doctors(" +
                "  doctor_id IDENTITY NOT NULL," +
                "  name VARCHAR_IGNORECASE," +
                "  specialty_id INT," +
                "  FOREIGN KEY (specialty_id) REFERENCES specialties(specialty_id)," +
                "  city_id INT," +
                "  FOREIGN KEY (city_id) REFERENCES cities(city_id)," +
                "  address VARCHAR," +
                "  PRIMARY KEY (doctor_id)" +
                ");";
        jdbcTemplate.execute(sql);//TODO catch and log error
    }

    class DoctorRowMapper implements RowMapper<Doctor> {
        @Override
        public Doctor mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            return setDoctorProperties(resultSet);
        }

    }

    @Override//TODO refactor this to not be repeated somehow
    public Doctor mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return setDoctorProperties(resultSet);

    }

    private static Doctor setDoctorProperties(ResultSet resultSet) throws SQLException{
        Doctor doctor = new Doctor();
        doctor.setDoctor_id(resultSet.getInt(DOCTOR_ID_COLUMN));
        doctor.setName(resultSet.getString(NAME_COLUMN));
        doctor.setAddress(resultSet.getString(ADDRESS_COLUMN));
        doctor.setCity(resultSet.getString(CITY_COLUMN));
        doctor.setSpecialty(resultSet.getString(SPECIALTY_COLUMN));
        return doctor;
    }

//    @Override
//    public List<Doctor> findAll() {
//        return jdbcTemplate.query("select * from doctors", new DoctorRowMapper());
//    }
    @Override
    public Doctor findById(int id) {

        String sql = "SELECT doctor_id, name, address, cities.name, specialties.name " +
                "FROM doctors, cities, specialties " +
                "LEFT JOIN specialties " +
                "ON doctors.specialty_id = specialties.specialty_id " +
                "LEFT JOIN cities " +
                "ON doctors.city_id = cities.city_id " +
                "where doctor_id=?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id },
                new BeanPropertyRowMapper<>(Doctor.class));
    }

    @Override
    public List<Doctor> findDoctorsBySpecialtyAndCity(String specialtyName, String cityName, String state) {
        specialtyName = specialtyName.trim();
        cityName = cityName.trim();
        state = state.trim();
        String specialtySql = "SELECT specialty_id " +
                "FROM specialties " +
                "WHERE name=?";
        String citySql = "SELECT city_id " +
                "FROM cities " +
                "WHERE name=? " +
                "AND state=?";
        int specialtyId = jdbcTemplate.queryForObject(specialtySql, new Object[] {specialtyName}, Integer.class);
        int cityId = jdbcTemplate.queryForObject(citySql, new Object[] {cityName, state}, Integer.class);

        String doctorSql = "SELECT doctors.doctor_id, doctors.name, doctors.address, cities.name AS city, specialties.name AS specialty  " +
                "FROM doctors " +
                "LEFT JOIN specialties " +
                "ON doctors.specialty_id = specialties.specialty_id " +
                "LEFT JOIN cities " +
                "ON doctors.city_id = cities.city_id " +
                "where doctors.specialty_id=? " +
                "AND doctors.city_id=? " +
                "LIMIT 5";

        return jdbcTemplate.query(doctorSql, new Object[] {specialtyId, cityId}, new DoctorRowMapper());

    }

    /*
     * int return val is number of rows affected
     */
//    @Override
//    public int insert(Doctor doctor) {
//        String separator = ", ";
//        String columnNames = NODE_ID_COLUMN + separator +
//                DATA_ID_COLUMN + separator +
//                EDGES_COLUMN + separator +
//                MAKE_COLUMN + separator +
//                MODEL_COLUMN + separator +
//                TYPE_COLUMN + separator +
//                PHONE_COLUMN + separator +
//                NAME_COLUMN + separator;
//
//        String sql = "insert into doctors (" + columnNames +") " + "values(?,?,?,?,?,?,?,?)";
//        return jdbcTemplate.update(sql, doctor.getDoctor_id(), doctor.getAddress(),
//                doctor.getSpecialty_id(), doctor.getCity_id(), doctor.getName());
//    }
}
