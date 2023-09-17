package com.example.class_studen.service.mapper;

import com.example.class_studen.dto.SubjTeachDto;
import com.example.class_studen.model.SubTeach;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-17T15:17:33+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class SubTeachMapperImpl extends SubTeachMapper {

    @Override
    public SubTeach toEntity(SubjTeachDto subjTeachDto) {
        if ( subjTeachDto == null ) {
            return null;
        }

        SubTeach.SubTeachBuilder subTeach = SubTeach.builder();

        subTeach.subjectId( subjTeachDto.getSubjectId() );
        subTeach.teacherId( subjTeachDto.getTeacherId() );
        subTeach.groupId( subjTeachDto.getGroupId() );

        return subTeach.build();
    }

    @Override
    public SubjTeachDto toDto(SubTeach subTeach) {
        if ( subTeach == null ) {
            return null;
        }

        SubjTeachDto.SubjTeachDtoBuilder subjTeachDto = SubjTeachDto.builder();

        subjTeachDto.id( subTeach.getId() );
        subjTeachDto.subjectId( subTeach.getSubjectId() );
        subjTeachDto.teacherId( subTeach.getTeacherId() );
        subjTeachDto.groupId( subTeach.getGroupId() );
        subjTeachDto.createdAt( subTeach.getCreatedAt() );
        subjTeachDto.updatedAt( subTeach.getUpdatedAt() );
        subjTeachDto.deletedAt( subTeach.getDeletedAt() );

        return subjTeachDto.build();
    }

    @Override
    public void update(SubTeach subTeach, SubjTeachDto subjTeachDto) {
        if ( subjTeachDto == null ) {
            return;
        }

        if ( subjTeachDto.getId() != null ) {
            subTeach.setId( subjTeachDto.getId() );
        }
        if ( subjTeachDto.getSubjectId() != null ) {
            subTeach.setSubjectId( subjTeachDto.getSubjectId() );
        }
        if ( subjTeachDto.getTeacherId() != null ) {
            subTeach.setTeacherId( subjTeachDto.getTeacherId() );
        }
        if ( subjTeachDto.getGroupId() != null ) {
            subTeach.setGroupId( subjTeachDto.getGroupId() );
        }
        if ( subjTeachDto.getCreatedAt() != null ) {
            subTeach.setCreatedAt( subjTeachDto.getCreatedAt() );
        }
        if ( subjTeachDto.getUpdatedAt() != null ) {
            subTeach.setUpdatedAt( subjTeachDto.getUpdatedAt() );
        }
        if ( subjTeachDto.getDeletedAt() != null ) {
            subTeach.setDeletedAt( subjTeachDto.getDeletedAt() );
        }
    }
}
