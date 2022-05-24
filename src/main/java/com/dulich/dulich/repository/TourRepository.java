package com.dulich.dulich.repository;

import java.util.List;

import com.dulich.dulich.model.Tour;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TourRepository extends JpaRepository<Tour, Long> {
    @Query(value = "SELECT * FROM Tour WHERE Tour.place LIKE %:keyword%", nativeQuery = true)
    List<Tour> findByKeyword(@Param("keyword") String keyword);
}
