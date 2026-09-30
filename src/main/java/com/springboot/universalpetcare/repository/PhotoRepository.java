package com.springboot.universalpetcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.universalpetcare.model.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long>{
    
}
