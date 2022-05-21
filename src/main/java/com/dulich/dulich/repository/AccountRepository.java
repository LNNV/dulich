package com.dulich.dulich.repository;

import java.util.Optional;

import com.dulich.dulich.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
    Optional<Account> findByPhone(String phone);
    Optional<Account> findByEmail(String email);
}
