package com.springboot.universalpetcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.universalpetcare.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
    
}
