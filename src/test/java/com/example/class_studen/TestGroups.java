package com.example.class_studen;

import com.example.class_studen.dto.GroupsDto;
import com.example.class_studen.dto.ResponseDto;
import com.example.class_studen.model.Groups;
import com.example.class_studen.repository.GroupsRepository;
import com.example.class_studen.service.GroupsService;
import com.example.class_studen.service.mapper.GroupsMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestGroups {
    private GroupsService groupsService;
    private GroupsMapper groupsMapper;
    private GroupsRepository groupsRepository;


    @BeforeEach
    void initObject() {
        this.groupsMapper = Mockito.mock(GroupsMapper.class);
        this.groupsRepository = Mockito.mock(GroupsRepository.class);
        this.groupsService = new GroupsService(groupsRepository, groupsMapper);
    }

    @Test
    void createPositive() {
        Groups groups = Groups.builder()
                .id(1)
                .name("Apple")
                .build();

        GroupsDto groupsDto = GroupsDto.builder()
                .id(1)
                .name("Apple")
                .build();
        when(this.groupsMapper.toEntity(any()))
                .thenReturn(groups);
        when(this.groupsMapper.toDto(any()))
                .thenReturn(groupsDto);

        ResponseDto<GroupsDto> group = this.groupsService.create(any());
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertNotNull(group.getData());
        Assertions.assertEquals(group.getData().getId(), 1);

        verify(this.groupsMapper, times(1)).toEntity(any());
        verify(this.groupsMapper, times(1)).toDto(any());
        verify(this.groupsRepository, times(1)).save(any());
    }

    @Test
    void getPositive() {
        GroupsDto groupsDto = GroupsDto.builder()
                .id(2)
                .name("banana")
                .build();

        Groups groups = Groups.builder()
                .id(2)
                .name("banana")
                .build();

        when(this.groupsMapper.toDto(any()))
                .thenReturn(groupsDto);

        when(this.groupsRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(groups));

        ResponseDto<GroupsDto> group = this.groupsService.get(any());
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertNotNull(group.getData());
        Assertions.assertEquals(group.getData().getId(), 2);

        verify(this.groupsMapper, times(1)).toDto(any());
        verify(this.groupsRepository, times(1)).findByIdAndDeletedAtIsNull(any());

    }

    @Test
    void getNegative() {
        when(this.groupsRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<GroupsDto> groups = this.groupsService.get(any());
        Assertions.assertEquals(groups.getCode(), -1);
        Assertions.assertNull(groups.getData());
        Assertions.assertFalse(groups.isSuccess());

        verify(this.groupsRepository, times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void updatePositive() {
        GroupsDto groupsDto = GroupsDto.builder()
                .id(2)
                .name("banana")
                .build();

        Groups groups = Groups.builder()
                .id(2)
                .name("banana")
                .build();

        when(this.groupsMapper.toDto(any()))
                .thenReturn(groupsDto);
        when(this.groupsRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(groups));

        ResponseDto<GroupsDto> group = this.groupsService.update(groupsDto, any());
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertNotNull(group.getData());
        Assertions.assertTrue(group.isSuccess());

        verify(this.groupsRepository, times(1)).findByIdAndDeletedAtIsNull(any());
        verify(this.groupsRepository, times(1)).save(any());
        verify(this.groupsMapper, times(1)).toDto(any());

    }

    @Test
    void updateNegative() {
        GroupsDto groupsDto = new GroupsDto();

        when(this.groupsRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<GroupsDto> group = this.groupsService.update(groupsDto, any());
        Assertions.assertFalse(group.isSuccess());
        Assertions.assertEquals(group.getCode(), -1);
        Assertions.assertNull(group.getData());

        verify(this.groupsRepository, times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void deletePositive() {
        GroupsDto groupsDto = GroupsDto.builder()
                .id(2)
                .name("banana")
                .build();

        Groups groups = Groups.builder()
                .id(2)
                .name("banana")
                .build();

        when(this.groupsRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(groups));

        when(this.groupsMapper.toDto(any()))
                .thenReturn(groupsDto);

        ResponseDto<GroupsDto> group = this.groupsService.delete(any());
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertTrue(group.isSuccess());
        Assertions.assertNotNull(group.getData());

        verify(this.groupsRepository, times(1)).save(any());
        verify(this.groupsRepository, times(1)).findByIdAndDeletedAtIsNull(any());
        verify(this.groupsMapper, times(1)).toDto(any());
    }

    @Test
    void deleteNegative() {

        when(this.groupsRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<GroupsDto> group = this.groupsService.delete(any());
        Assertions.assertFalse(group.isSuccess());
        Assertions.assertNull(group.getData());
        Assertions.assertEquals(group.getCode(), -1);

        verify(this.groupsRepository, times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void getAllPositive() {
        Groups groups = Groups.builder()
                .id(2)
                .name("banana")
                .build();

        GroupsDto groupsDto = GroupsDto.builder()
                .id(2)
                .name("banana")
                .build();

        when(this.groupsRepository.findAllElements())
                .thenReturn(List.of(groups));
        when(this.groupsMapper.toDto(any()))
                .thenReturn(groupsDto);

        ResponseDto<List<GroupsDto>> group = this.groupsService.getAll();
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertNotNull(group.getData());
        Assertions.assertTrue(group.isSuccess());


        verify(this.groupsRepository, times(1)).findAllElements();
        verify(this.groupsMapper, times(1)).toDto(any());
    }

    @Test
    void getAllNegative() {
        when(this.groupsRepository.findAllElements())
                .thenReturn(List.of());

        ResponseDto<List<GroupsDto>> group = this.groupsService.getAll();
        Assertions.assertFalse(group.isSuccess());
        Assertions.assertNull(group.getData());
        Assertions.assertEquals(group.getCode(), -1);

        verify(this.groupsRepository, times(1)).findAllElements();
    }
}
