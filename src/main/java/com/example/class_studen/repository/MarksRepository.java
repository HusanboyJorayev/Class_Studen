package com.example.class_studen.repository;

import com.example.class_studen.model.Groups;
import com.example.class_studen.model.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarksRepository extends JpaRepository<Marks,Integer> {
    Optional<Marks>findByIdAndDeletedAtIsNull(Integer id);

    @Query(
            value = "select * from groups",
            nativeQuery = true
    )
    List<Marks> findAllElements();
}
