package com.example.class_studen.repository;

import com.example.class_studen.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface USerRepository extends JpaRepository<User,Integer> {
    Optional<User>findByUsernameAndDeletedAtIsNull(String username);
    Optional<User>findByUsernameAndEnabledIsTrueAndDeletedAtIsNull(String username);
}
