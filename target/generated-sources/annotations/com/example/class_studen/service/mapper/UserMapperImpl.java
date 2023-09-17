package com.example.class_studen.service.mapper;

import com.example.class_studen.dto.UserDto;
import com.example.class_studen.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-17T15:17:33+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.userId( user.getUserId() );
        userDto.firstname( user.getFirstname() );
        userDto.lastname( user.getLastname() );
        userDto.username( user.getUsername() );
        userDto.email( user.getEmail() );
        userDto.enabled( user.getEnabled() );
        userDto.role( user.getRole() );
        userDto.createdAt( user.getCreatedAt() );
        userDto.updatedAt( user.getUpdatedAt() );
        userDto.deletedAt( user.getDeletedAt() );

        userDto.password( passwordEncoder.encode(user.getPassword()) );

        return userDto.build();
    }

    @Override
    public User toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.firstname( dto.getFirstname() );
        user.lastname( dto.getLastname() );
        user.username( dto.getUsername() );
        user.password( dto.getPassword() );
        user.email( dto.getEmail() );
        user.role( dto.getRole() );

        user.enabled( false );

        return user.build();
    }

    @Override
    public void update(User user, UserDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getUserId() != null ) {
            user.setUserId( dto.getUserId() );
        }
        if ( dto.getFirstname() != null ) {
            user.setFirstname( dto.getFirstname() );
        }
        if ( dto.getLastname() != null ) {
            user.setLastname( dto.getLastname() );
        }
        if ( dto.getUsername() != null ) {
            user.setUsername( dto.getUsername() );
        }
        if ( dto.getEmail() != null ) {
            user.setEmail( dto.getEmail() );
        }
        if ( dto.getEnabled() != null ) {
            user.setEnabled( dto.getEnabled() );
        }
        if ( dto.getRole() != null ) {
            user.setRole( dto.getRole() );
        }
        if ( dto.getCreatedAt() != null ) {
            user.setCreatedAt( dto.getCreatedAt() );
        }
        if ( dto.getUpdatedAt() != null ) {
            user.setUpdatedAt( dto.getUpdatedAt() );
        }
        if ( dto.getDeletedAt() != null ) {
            user.setDeletedAt( dto.getDeletedAt() );
        }

        user.setPassword( passwordEncoder.encode(user.getPassword()) );
    }
}
