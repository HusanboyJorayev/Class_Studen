package com.example.class_studen.service.mapper;

import com.example.class_studen.dto.UserDto;
import com.example.class_studen.model.User;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(user.getPassword()))")
    public abstract UserDto toDto(User user);

    @Mapping(ignore = true, target = "userId")
    @Mapping(ignore = true, target = "createdAt")
    @Mapping(ignore = true, target = "updatedAt")
    @Mapping(ignore = true, target = "deletedAt")
    @Mapping(target = "enabled", expression = "java(false)")
    public abstract User toEntity(UserDto dto);

    @Mapping(target = "password", expression = "java(passwordEncoder.encode(user.getPassword()))")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget User user, UserDto dto);

    public void view() {
        User user = new User();
        passwordEncoder.encode(user.getPassword());
    }
}
