package com.dulich.dulich.repository;

import java.util.List;
import java.util.Optional;

import com.dulich.dulich.model.Account;
import com.dulich.dulich.model.Book;
import com.dulich.dulich.model.Tour;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTour(Tour tour);
    List<Book> findByAccount(Account account);

    @Query(value = "SELECT Book.* FROM Book LEFT JOIN Account ON Account.id = Book.userid WHERE Account.email LIKE %:keyword% OR Account.fullname LIKE %:keyword% OR Account.phone LIKE %:keyword%", nativeQuery = true)
    List<Book> findByKeyword(@Param("keyword") String keyword);
}
