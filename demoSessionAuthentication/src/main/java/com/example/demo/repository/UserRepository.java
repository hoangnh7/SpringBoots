package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.model.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByEmail(String email);
}
