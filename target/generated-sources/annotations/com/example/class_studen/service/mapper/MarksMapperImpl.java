package com.example.class_studen.service.mapper;

import com.example.class_studen.dto.MarksDto;
import com.example.class_studen.model.Marks;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-17T15:17:33+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class MarksMapperImpl extends MarksMapper {

    @Override
    public Marks toEntity(MarksDto marksDto) {
        if ( marksDto == null ) {
            return null;
        }

        Marks.MarksBuilder marks = Marks.builder();

        marks.studentId( marksDto.getStudentId() );
        marks.subjectId( marksDto.getSubjectId() );
        marks.date( marksDto.getDate() );

        return marks.build();
    }

    @Override
    public MarksDto toDto(Marks marks) {
        if ( marks == null ) {
            return null;
        }

        MarksDto.MarksDtoBuilder marksDto = MarksDto.builder();

        marksDto.id( marks.getId() );
        marksDto.studentId( marks.getStudentId() );
        marksDto.subjectId( marks.getSubjectId() );
        marksDto.date( marks.getDate() );
        marksDto.createdAt( marks.getCreatedAt() );
        marksDto.updatedAt( marks.getUpdatedAt() );
        marksDto.deletedAt( marks.getDeletedAt() );

        return marksDto.build();
    }

    @Override
    public void update(Marks marks, MarksDto marksDto) {
        if ( marksDto == null ) {
            return;
        }

        if ( marksDto.getId() != null ) {
            marks.setId( marksDto.getId() );
        }
        if ( marksDto.getStudentId() != null ) {
            marks.setStudentId( marksDto.getStudentId() );
        }
        if ( marksDto.getSubjectId() != null ) {
            marks.setSubjectId( marksDto.getSubjectId() );
        }
        if ( marksDto.getDate() != null ) {
            marks.setDate( marksDto.getDate() );
        }
        if ( marksDto.getCreatedAt() != null ) {
            marks.setCreatedAt( marksDto.getCreatedAt() );
        }
        if ( marksDto.getUpdatedAt() != null ) {
            marks.setUpdatedAt( marksDto.getUpdatedAt() );
        }
        if ( marksDto.getDeletedAt() != null ) {
            marks.setDeletedAt( marksDto.getDeletedAt() );
        }
    }
}
