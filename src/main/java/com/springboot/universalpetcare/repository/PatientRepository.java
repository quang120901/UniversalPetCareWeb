package com.springboot.universalpetcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.universalpetcare.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}