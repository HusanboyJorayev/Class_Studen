package com.example.class_studen.service.mapper;

import com.example.class_studen.dto.SubjectsDto;
import com.example.class_studen.model.Subjects;
import java.util.stream.Collectors;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-17T15:17:33+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class SubjectsMapperImpl extends SubjectsMapper {

    @Override
    public Subjects toEntity(SubjectsDto subjectsDto) {
        if ( subjectsDto == null ) {
            return null;
        }

        Subjects.SubjectsBuilder subjects = Subjects.builder();

        subjects.title( subjectsDto.getTitle() );

        return subjects.build();
    }

    @Override
    public SubjectsDto toDto(Subjects subjects) {
        if ( subjects == null ) {
            return null;
        }

        SubjectsDto.SubjectsDtoBuilder subjectsDto = SubjectsDto.builder();

        subjectsDto.id( subjects.getId() );
        subjectsDto.title( subjects.getTitle() );
        subjectsDto.createdAt( subjects.getCreatedAt() );
        subjectsDto.updatedAt( subjects.getUpdatedAt() );
        subjectsDto.deletedAt( subjects.getDeletedAt() );

        return subjectsDto.build();
    }

    @Override
    public SubjectsDto toDtoWihMark(Subjects subjects) {
        if ( subjects == null ) {
            return null;
        }

        SubjectsDto.SubjectsDtoBuilder subjectsDto = SubjectsDto.builder();

        subjectsDto.id( subjects.getId() );
        subjectsDto.title( subjects.getTitle() );
        subjectsDto.createdAt( subjects.getCreatedAt() );
        subjectsDto.updatedAt( subjects.getUpdatedAt() );
        subjectsDto.deletedAt( subjects.getDeletedAt() );

        subjectsDto.marks( subjects.getMarks().stream().map(this.marksMapper::toDto).collect(Collectors.toSet()) );

        return subjectsDto.build();
    }

    @Override
    public SubjectsDto toDtoWithSubTeach(Subjects subjects) {
        if ( subjects == null ) {
            return null;
        }

        SubjectsDto.SubjectsDtoBuilder subjectsDto = SubjectsDto.builder();

        subjectsDto.id( subjects.getId() );
        subjectsDto.title( subjects.getTitle() );
        subjectsDto.createdAt( subjects.getCreatedAt() );
        subjectsDto.updatedAt( subjects.getUpdatedAt() );
        subjectsDto.deletedAt( subjects.getDeletedAt() );

        subjectsDto.subjTeach( subjects.getSubjTeach().stream().map(this.subTeachMapper::toDto).collect(Collectors.toSet()) );

        return subjectsDto.build();
    }

    @Override
    public void update(Subjects subjects, SubjectsDto subjectsDto) {
        if ( subjectsDto == null ) {
            return;
        }

        if ( subjectsDto.getId() != null ) {
            subjects.setId( subjectsDto.getId() );
        }
        if ( subjectsDto.getTitle() != null ) {
            subjects.setTitle( subjectsDto.getTitle() );
        }
        if ( subjectsDto.getCreatedAt() != null ) {
            subjects.setCreatedAt( subjectsDto.getCreatedAt() );
        }
        if ( subjectsDto.getUpdatedAt() != null ) {
            subjects.setUpdatedAt( subjectsDto.getUpdatedAt() );
        }
        if ( subjectsDto.getDeletedAt() != null ) {
            subjects.setDeletedAt( subjectsDto.getDeletedAt() );
        }
    }
}
