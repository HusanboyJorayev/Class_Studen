package com.example.class_studen.service.mapper;

import com.example.class_studen.dto.StudentsDto;
import com.example.class_studen.model.Students;
import java.util.stream.Collectors;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-17T15:17:33+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class StudentsMapperImpl extends StudentsMapper {

    @Override
    public Students toEntity(StudentsDto studentsDto) {
        if ( studentsDto == null ) {
            return null;
        }

        Students.StudentsBuilder students = Students.builder();

        students.firstname( studentsDto.getFirstname() );
        students.lastname( studentsDto.getLastname() );
        students.groupId( studentsDto.getGroupId() );

        return students.build();
    }

    @Override
    public StudentsDto toDto(Students students) {
        if ( students == null ) {
            return null;
        }

        StudentsDto.StudentsDtoBuilder studentsDto = StudentsDto.builder();

        studentsDto.id( students.getId() );
        studentsDto.firstname( students.getFirstname() );
        studentsDto.lastname( students.getLastname() );
        studentsDto.groupId( students.getGroupId() );
        studentsDto.createdAt( students.getCreatedAt() );
        studentsDto.updatedAt( students.getUpdatedAt() );
        studentsDto.deletedAt( students.getDeletedAt() );

        return studentsDto.build();
    }

    @Override
    public StudentsDto toDtoWithMark(Students students) {
        if ( students == null ) {
            return null;
        }

        StudentsDto.StudentsDtoBuilder studentsDto = StudentsDto.builder();

        studentsDto.id( students.getId() );
        studentsDto.firstname( students.getFirstname() );
        studentsDto.lastname( students.getLastname() );
        studentsDto.groupId( students.getGroupId() );
        studentsDto.createdAt( students.getCreatedAt() );
        studentsDto.updatedAt( students.getUpdatedAt() );
        studentsDto.deletedAt( students.getDeletedAt() );

        studentsDto.marks( students.getMarks().stream().map(this.marksMapper::toDto).collect(Collectors.toSet()) );

        return studentsDto.build();
    }

    @Override
    public void update(Students students, StudentsDto studentsDto) {
        if ( studentsDto == null ) {
            return;
        }

        if ( studentsDto.getId() != null ) {
            students.setId( studentsDto.getId() );
        }
        if ( studentsDto.getFirstname() != null ) {
            students.setFirstname( studentsDto.getFirstname() );
        }
        if ( studentsDto.getLastname() != null ) {
            students.setLastname( studentsDto.getLastname() );
        }
        if ( studentsDto.getGroupId() != null ) {
            students.setGroupId( studentsDto.getGroupId() );
        }
        if ( studentsDto.getCreatedAt() != null ) {
            students.setCreatedAt( studentsDto.getCreatedAt() );
        }
        if ( studentsDto.getUpdatedAt() != null ) {
            students.setUpdatedAt( studentsDto.getUpdatedAt() );
        }
        if ( studentsDto.getDeletedAt() != null ) {
            students.setDeletedAt( studentsDto.getDeletedAt() );
        }
    }
}
