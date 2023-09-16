package com.example.class_studen.service.mapper;

import com.example.class_studen.dto.GroupsDto;
import com.example.class_studen.dto.SubjTeachDto;
import com.example.class_studen.model.Groups;
import com.example.class_studen.model.SubTeach;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class SubTeachMapper {

    @Mapping(ignore = true,target = "id")
    @Mapping(ignore = true,target = "createdAt")
    @Mapping(ignore = true,target = "updatedAt")
    @Mapping(ignore = true,target = "deletedAt")
    public  abstract SubTeach toEntity(SubjTeachDto subjTeachDto);


    public abstract SubjTeachDto toDto(SubTeach subTeach);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget SubTeach subTeach, SubjTeachDto subjTeachDto);
}
