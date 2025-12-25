package com.springboot.universalpetcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.universalpetcare.model.Veterinarian;

public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {
    
}
