package com.example.class_studen.service.mapper;


import com.example.class_studen.dto.TeachersDto;
import com.example.class_studen.model.Teachers;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring",imports = Collectors.class)
public abstract class TeachersMapper {

    @Autowired
    protected SubTeachMapper subTeachMapper;
    @Mapping(ignore = true,target = "id")
    @Mapping(ignore = true,target = "createdAt")
    @Mapping(ignore = true,target = "updatedAt")
    @Mapping(ignore = true,target = "deletedAt")
    @Mapping(ignore = true,target = "subjTeach")
    public  abstract Teachers toEntity(TeachersDto teachersDto);


    @Mapping(ignore = true,target = "subjTeach")
    public abstract TeachersDto toDto(Teachers teachers);

    @Mapping(target = "subjTeach",expression = "java(teachers.getSubjTeach().stream().map(this.subTeachMapper::toDto).collect(Collectors.toSet()))")
    public abstract TeachersDto toDtoWithSubTeach(Teachers teachers);
    @Mapping(ignore = true,target = "subjTeach")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Teachers teachers, TeachersDto teachersDto);

    public void view(Teachers teachers){
        TeachersDto dto=new TeachersDto();
        dto.setSubjTeach(teachers.getSubjTeach().stream().map(this.subTeachMapper::toDto).collect(Collectors.toSet()));
    }
}
