package com.example.demo.Repository;

import com.example.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Integer> {

    Users findByUsername(String username);
}
