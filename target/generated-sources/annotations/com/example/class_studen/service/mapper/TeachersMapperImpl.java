package com.example.class_studen.service.mapper;

import com.example.class_studen.dto.TeachersDto;
import com.example.class_studen.model.Teachers;
import java.util.stream.Collectors;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-17T15:17:33+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class TeachersMapperImpl extends TeachersMapper {

    @Override
    public Teachers toEntity(TeachersDto teachersDto) {
        if ( teachersDto == null ) {
            return null;
        }

        Teachers.TeachersBuilder teachers = Teachers.builder();

        teachers.firstname( teachersDto.getFirstname() );
        teachers.lastname( teachersDto.getLastname() );

        return teachers.build();
    }

    @Override
    public TeachersDto toDto(Teachers teachers) {
        if ( teachers == null ) {
            return null;
        }

        TeachersDto.TeachersDtoBuilder teachersDto = TeachersDto.builder();

        teachersDto.id( teachers.getId() );
        teachersDto.firstname( teachers.getFirstname() );
        teachersDto.lastname( teachers.getLastname() );
        teachersDto.createdAt( teachers.getCreatedAt() );
        teachersDto.updatedAt( teachers.getUpdatedAt() );
        teachersDto.deletedAt( teachers.getDeletedAt() );

        return teachersDto.build();
    }

    @Override
    public TeachersDto toDtoWithSubTeach(Teachers teachers) {
        if ( teachers == null ) {
            return null;
        }

        TeachersDto.TeachersDtoBuilder teachersDto = TeachersDto.builder();

        teachersDto.id( teachers.getId() );
        teachersDto.firstname( teachers.getFirstname() );
        teachersDto.lastname( teachers.getLastname() );
        teachersDto.createdAt( teachers.getCreatedAt() );
        teachersDto.updatedAt( teachers.getUpdatedAt() );
        teachersDto.deletedAt( teachers.getDeletedAt() );

        teachersDto.subjTeach( teachers.getSubjTeach().stream().map(this.subTeachMapper::toDto).collect(Collectors.toSet()) );

        return teachersDto.build();
    }

    @Override
    public void update(Teachers teachers, TeachersDto teachersDto) {
        if ( teachersDto == null ) {
            return;
        }

        if ( teachersDto.getId() != null ) {
            teachers.setId( teachersDto.getId() );
        }
        if ( teachersDto.getFirstname() != null ) {
            teachers.setFirstname( teachersDto.getFirstname() );
        }
        if ( teachersDto.getLastname() != null ) {
            teachers.setLastname( teachersDto.getLastname() );
        }
        if ( teachersDto.getCreatedAt() != null ) {
            teachers.setCreatedAt( teachersDto.getCreatedAt() );
        }
        if ( teachersDto.getUpdatedAt() != null ) {
            teachers.setUpdatedAt( teachersDto.getUpdatedAt() );
        }
        if ( teachersDto.getDeletedAt() != null ) {
            teachers.setDeletedAt( teachersDto.getDeletedAt() );
        }
    }
}
