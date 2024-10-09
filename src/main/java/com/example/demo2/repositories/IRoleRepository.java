package com.example.demo2.repositories;

import com.example.demo2.models.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Roles,Long> {
}
