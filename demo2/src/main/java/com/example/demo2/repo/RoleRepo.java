package com.example.demo2.repo;

import com.example.demo2.domain.Role;
import com.example.demo2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByName(String username);
}
