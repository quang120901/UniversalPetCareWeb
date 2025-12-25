package com.springboot.universalpetcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.universalpetcare.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    
}
