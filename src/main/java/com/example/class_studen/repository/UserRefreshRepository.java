package com.example.class_studen.repository;

import com.example.class_studen.model.User;
import com.example.class_studen.model.UserRefresh;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRefreshRepository extends CrudRepository<UserRefresh,String> {
}
