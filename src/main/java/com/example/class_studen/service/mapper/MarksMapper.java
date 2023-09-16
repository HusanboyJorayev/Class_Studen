package com.example.class_studen.service.mapper;

import com.example.class_studen.dto.GroupsDto;
import com.example.class_studen.dto.MarksDto;
import com.example.class_studen.model.Groups;
import com.example.class_studen.model.Marks;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class MarksMapper {

    @Mapping(ignore = true,target = "id")
    @Mapping(ignore = true,target = "createdAt")
    @Mapping(ignore = true,target = "updatedAt")
    @Mapping(ignore = true,target = "deletedAt")
    public  abstract Marks toEntity(MarksDto marksDto);


    public abstract MarksDto toDto(Marks marks);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget Marks marks, MarksDto marksDto);
}
