package com.example.class_studen.service.mapper;

import com.example.class_studen.dto.GroupsDto;
import com.example.class_studen.dto.StudentsDto;
import com.example.class_studen.model.Groups;
import com.example.class_studen.model.Students;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class StudentsMapper {

    @Autowired
    protected MarksMapper marksMapper;

    @Mapping(ignore = true,target = "id")
    @Mapping(ignore = true,target = "createdAt")
    @Mapping(ignore = true,target = "updatedAt")
    @Mapping(ignore = true,target = "deletedAt")
    @Mapping(ignore = true,target = "marks")
    public  abstract Students toEntity(StudentsDto studentsDto);


    @Mapping(ignore = true,target = "marks")
    public abstract StudentsDto toDto(Students students);

    @Mapping(target = "marks",expression = "java(students.getMarks().stream().map(this.marksMapper::toDto).collect(Collectors.toSet()))")
    public abstract StudentsDto toDtoWithMark(Students students);

    @Mapping(ignore = true,target = "marks")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Students students, StudentsDto studentsDto);

    public void view(Students students){
        StudentsDto dto=new StudentsDto();
        dto.setMarks(students.getMarks().stream().map(this.marksMapper::toDto).collect(Collectors.toSet()));
    }
}
