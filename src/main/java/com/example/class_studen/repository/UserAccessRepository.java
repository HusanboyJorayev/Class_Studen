package com.example.class_studen.repository;

import com.example.class_studen.model.User;
import com.example.class_studen.model.UserAccess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccessRepository extends CrudRepository<UserAccess,String> {
}
