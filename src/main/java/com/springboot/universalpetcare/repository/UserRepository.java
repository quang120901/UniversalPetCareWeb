package com.springboot.universalpetcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.universalpetcare.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}
