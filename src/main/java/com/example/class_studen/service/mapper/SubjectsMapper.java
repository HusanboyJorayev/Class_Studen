package com.example.class_studen.service.mapper;

import com.example.class_studen.dto.GroupsDto;
import com.example.class_studen.dto.SubjectsDto;
import com.example.class_studen.model.Groups;
import com.example.class_studen.model.Subjects;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class SubjectsMapper {

    @Autowired
    protected MarksMapper marksMapper;

    @Autowired
    protected SubTeachMapper subTeachMapper;

    @Mapping(ignore = true,target = "id")
    @Mapping(ignore = true,target = "createdAt")
    @Mapping(ignore = true,target = "updatedAt")
    @Mapping(ignore = true,target = "deletedAt")
    @Mapping(ignore = true,target = "marks")
    @Mapping(ignore = true,target = "subjTeach")
    public  abstract Subjects toEntity(SubjectsDto subjectsDto);


    @Mapping(ignore = true,target = "marks")
    @Mapping(ignore = true,target = "subjTeach")
    public abstract SubjectsDto toDto(Subjects subjects);

    @Mapping(ignore = true,target = "subjTeach")
    @Mapping(target = "marks",expression = "java(subjects.getMarks().stream().map(this.marksMapper::toDto).collect(Collectors.toSet()))")
    public abstract SubjectsDto toDtoWihMark(Subjects subjects);

    @Mapping(ignore = true,target = "marks")
    @Mapping(target = "subjTeach",expression = "java(subjects.getSubjTeach().stream().map(this.subTeachMapper::toDto).collect(Collectors.toSet()))")
    public abstract SubjectsDto toDtoWithSubTeach(Subjects subjects);

    @Mapping(ignore = true,target = "marks")
    @Mapping(ignore = true,target = "subjTeach")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Subjects subjects, SubjectsDto subjectsDto);

    public void view(Subjects subjects){
        SubjectsDto dto=new SubjectsDto();
        dto.setSubjTeach(subjects.getSubjTeach().stream().map(this.subTeachMapper::toDto).collect(Collectors.toSet()));
    }
}
