package com.dulich.dulich.repository;

import java.util.List;

import com.dulich.dulich.model.Feedback;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    @Query(value = "SELECT * FROM Feedback", nativeQuery = true)
    List<Feedback> findByKeyword(@Param("keyword") String keyword);
}
