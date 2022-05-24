package com.dulich.dulich.repository;

import java.util.List;

import com.dulich.dulich.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM Book", nativeQuery = true)
    List<Book> findByKeyword(@Param("keyword") String keyword);
}
