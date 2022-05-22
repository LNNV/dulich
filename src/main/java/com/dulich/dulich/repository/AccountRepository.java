package com.dulich.dulich.repository;

import java.util.List;
import java.util.Optional;

import com.dulich.dulich.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
    Optional<Account> findByPhone(String phone);
    Optional<Account> findByEmail(String email);

    @Query(value = "SELECT * FROM Account WHERE Account.role != 'admin'", nativeQuery = true)
    List<Account> findByKeyword(@Param("keyword") String keyword);
}
