package com.example.demo2.repositories;

import com.example.demo2.models.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);

}