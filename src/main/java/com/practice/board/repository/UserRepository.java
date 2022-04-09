package com.practice.board.repository;

import com.practice.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT a FROM User a WHERE a.phoneNumber = ?1")
    Optional<User> findByPhone(String emailId);
}
