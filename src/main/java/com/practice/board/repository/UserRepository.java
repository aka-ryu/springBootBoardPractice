package com.practice.board.repository;

import com.practice.board.dto.UserDTO;
import com.practice.board.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
