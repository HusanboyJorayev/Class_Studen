package com.example.class_studen.repository;

import com.example.class_studen.model.SubTeach;
import com.example.class_studen.model.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeachersRepository extends JpaRepository<Teachers,Integer> {
    Optional<Teachers>findByIdAndDeletedAtIsNull(Integer id);

    @Query(
            value = "select * from groups",
            nativeQuery = true
    )
    List<Teachers> findAllElements();
}
