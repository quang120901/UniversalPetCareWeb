package com.springboot.universalpetcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.universalpetcare.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query ("SELECT DISTINCT p.type FROM Pet p")
    static List<String> getDistinctPetTypes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDistinctPetTypes'");
    }

    @Query ("SELECT DISTINCT p.color FROM Pet p")
    static List<String> getDistinctPetColors() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDistinctPetColors'");
    }

    @Query ("SELECT DISTINCT p.breed FROM Pet p WHERE p.type = :petType")
    static List<String> getDistinctPetBreedsByPetType(String petType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDistinctPetBreedsByPetType'");
    }
    
}
