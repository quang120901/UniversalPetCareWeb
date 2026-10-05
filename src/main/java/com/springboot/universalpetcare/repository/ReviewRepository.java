package com.springboot.universalpetcare.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.universalpetcare.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
    @Query ("SELECT r FROM Review r WHERE r.patient.id =:userId OR r.veterinarian.id =:userId ")
    Page<Review> findAllByUserId(@Param ("userId") Long userId, Pageable pageable);

    List<Review> findByVeterinarianId(Long veterinarianId);
}
