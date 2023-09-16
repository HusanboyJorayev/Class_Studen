package com.example.class_studen.service.mapper;

import com.example.class_studen.dto.GroupsDto;
import com.example.class_studen.model.Groups;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class GroupsMapper {

    @Autowired
    protected StudentsMapper studentsMapper;

    @Autowired
    protected SubTeachMapper subTeachMapper;

    @Mapping(ignore = true,target = "id")
    @Mapping(ignore = true,target = "createdAt")
    @Mapping(ignore = true,target = "updatedAt")
    @Mapping(ignore = true,target = "deletedAt")
    @Mapping(ignore = true,target = "student")
    @Mapping(ignore = true,target = "subjTeach")
    public  abstract Groups toEntity(GroupsDto groupsDto);


    @Mapping(ignore = true,target = "student")
    @Mapping(ignore = true,target = "subjTeach")
    public abstract GroupsDto toDto(Groups groups);

    @Mapping(ignore = true,target = "subjTeach")
    @Mapping(target = "student",expression = "java(groups.getStudent().stream().map(this.studentsMapper::toDto).collect(Collectors.toSet()))")
    public abstract GroupsDto toDtoWithStudents(Groups groups);

    @Mapping(ignore = true,target = "student")
    @Mapping(target = "subjTeach",expression = "java(groups.getSubjTeach().stream().map(this.subTeachMapper::toDto).collect(Collectors.toSet()))")
    public abstract GroupsDto toDtoWithSubTeach(Groups groups);

    @Mapping(ignore = true,target = "student")
    @Mapping(ignore = true,target = "subjTeach")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Groups groups, GroupsDto groupsDto);


    public void view(Groups groups){
        GroupsDto dto=new GroupsDto();
        dto.setSubjTeach(groups.getSubjTeach().stream().map(this.subTeachMapper::toDto).collect(Collectors.toSet()));
    }
}
