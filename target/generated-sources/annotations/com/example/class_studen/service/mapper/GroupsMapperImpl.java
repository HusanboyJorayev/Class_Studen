package com.example.class_studen.service.mapper;

import com.example.class_studen.dto.GroupsDto;
import com.example.class_studen.model.Groups;
import java.util.stream.Collectors;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-17T15:17:33+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
@Component
public class GroupsMapperImpl extends GroupsMapper {

    @Override
    public Groups toEntity(GroupsDto groupsDto) {
        if ( groupsDto == null ) {
            return null;
        }

        Groups.GroupsBuilder groups = Groups.builder();

        groups.name( groupsDto.getName() );

        return groups.build();
    }

    @Override
    public GroupsDto toDto(Groups groups) {
        if ( groups == null ) {
            return null;
        }

        GroupsDto.GroupsDtoBuilder groupsDto = GroupsDto.builder();

        groupsDto.id( groups.getId() );
        groupsDto.name( groups.getName() );
        groupsDto.createdAt( groups.getCreatedAt() );
        groupsDto.updatedAt( groups.getUpdatedAt() );
        groupsDto.deletedAt( groups.getDeletedAt() );

        return groupsDto.build();
    }

    @Override
    public GroupsDto toDtoWithStudents(Groups groups) {
        if ( groups == null ) {
            return null;
        }

        GroupsDto.GroupsDtoBuilder groupsDto = GroupsDto.builder();

        groupsDto.id( groups.getId() );
        groupsDto.name( groups.getName() );
        groupsDto.createdAt( groups.getCreatedAt() );
        groupsDto.updatedAt( groups.getUpdatedAt() );
        groupsDto.deletedAt( groups.getDeletedAt() );

        groupsDto.student( groups.getStudent().stream().map(this.studentsMapper::toDto).collect(Collectors.toSet()) );

        return groupsDto.build();
    }

    @Override
    public GroupsDto toDtoWithSubTeach(Groups groups) {
        if ( groups == null ) {
            return null;
        }

        GroupsDto.GroupsDtoBuilder groupsDto = GroupsDto.builder();

        groupsDto.id( groups.getId() );
        groupsDto.name( groups.getName() );
        groupsDto.createdAt( groups.getCreatedAt() );
        groupsDto.updatedAt( groups.getUpdatedAt() );
        groupsDto.deletedAt( groups.getDeletedAt() );

        groupsDto.subjTeach( groups.getSubjTeach().stream().map(this.subTeachMapper::toDto).collect(Collectors.toSet()) );

        return groupsDto.build();
    }

    @Override
    public void update(Groups groups, GroupsDto groupsDto) {
        if ( groupsDto == null ) {
            return;
        }

        if ( groupsDto.getId() != null ) {
            groups.setId( groupsDto.getId() );
        }
        if ( groupsDto.getName() != null ) {
            groups.setName( groupsDto.getName() );
        }
        if ( groupsDto.getCreatedAt() != null ) {
            groups.setCreatedAt( groupsDto.getCreatedAt() );
        }
        if ( groupsDto.getUpdatedAt() != null ) {
            groups.setUpdatedAt( groupsDto.getUpdatedAt() );
        }
        if ( groupsDto.getDeletedAt() != null ) {
            groups.setDeletedAt( groupsDto.getDeletedAt() );
        }
    }
}
