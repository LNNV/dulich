package com.dulich.dulich.repository;

import java.util.List;

import com.dulich.dulich.model.News;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    @Query(value = "SELECT * FROM News WHERE News.topic LIKE %:keyword% OR News.content LIKE %:keyword% OR News.intro LIKE %:keyword% OR News.source LIKE %:keyword%", nativeQuery = true)
    List<News> findByKeyword(@Param("keyword") String keyword);
}