package com.example.class_studen.repository;

import com.example.class_studen.model.Students;
import com.example.class_studen.model.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectsRepository extends JpaRepository<Subjects,Integer> {
    Optional<Subjects>findByIdAndDeletedAtIsNull(Integer id);

    @Query(
            value = "select * from groups",
            nativeQuery = true
    )
    List<Subjects> findAllElements();
}
