package com.example.class_studen;

import com.example.class_studen.dto.MarksDto;
import com.example.class_studen.dto.ResponseDto;
import com.example.class_studen.dto.TeachersDto;
import com.example.class_studen.model.Marks;
import com.example.class_studen.model.Teachers;
import com.example.class_studen.repository.TeachersRepository;
import com.example.class_studen.service.TeachersService;
import com.example.class_studen.service.mapper.TeachersMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestTeacher {

    private TeachersMapper teachersMapper;
    private TeachersRepository teachersRepository;
    private TeachersService teachersService;

    @BeforeEach
    void initObject() {
        this.teachersMapper = Mockito.mock(TeachersMapper.class);
        this.teachersRepository = Mockito.mock(TeachersRepository.class);
        this.teachersService = new TeachersService(teachersRepository, teachersMapper);
    }

    @Test
    void createPositive() {
        Teachers teachers = Teachers.builder()
                .id(1)
                .firstname("Husanboy")
                .lastname("Jorayev")
                .build();

        TeachersDto teachersDto = TeachersDto.builder()
                .id(1)
                .firstname("Husanboy")
                .lastname("Jorayev")
                .build();
        when(this.teachersMapper.toEntity(any()))
                .thenReturn(teachers);
        when(this.teachersMapper.toDto(any()))
                .thenReturn(teachersDto);

        ResponseDto<TeachersDto> mark = this.teachersService.create(any());
        Assertions.assertEquals(mark.getCode(), 0);
        Assertions.assertNotNull(mark.getData());
        Assertions.assertEquals(mark.getData().getId(), 1);

        verify(this.teachersMapper, times(1)).toEntity(any());
        verify(this.teachersMapper, times(1)).toDto(any());
        verify(this.teachersRepository, times(1)).save(any());
    }

    @Test
    void getPositive() {
        Teachers teachers = Teachers.builder()
                .id(1)
                .firstname("Husanboy")
                .lastname("Jorayev")
                .build();

        TeachersDto teachersDto = TeachersDto.builder()
                .id(1)
                .firstname("Husanboy")
                .lastname("Jorayev")
                .build();

        when(this.teachersMapper.toDto(any()))
                .thenReturn(teachersDto);

        when(this.teachersRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(teachers));

        ResponseDto<TeachersDto> group = this.teachersService.get(any());
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertNotNull(group.getData());
        Assertions.assertEquals(group.getData().getId(), 1);

        verify(this.teachersMapper, times(1)).toDto(any());
        verify(this.teachersRepository, times(1)).findByIdAndDeletedAtIsNull(any());

    }

    @Test
    void getNegative() {
        when(this.teachersRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<TeachersDto> groups = this.teachersService.get(any());
        Assertions.assertEquals(groups.getCode(), -1);
        Assertions.assertNull(groups.getData());
        Assertions.assertFalse(groups.isSuccess());

        verify(this.teachersRepository, times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void updatePositive() {
        Teachers teachers = Teachers.builder()
                .id(1)
                .firstname("Husanboy")
                .lastname("Jorayev")
                .build();

        TeachersDto teachersDto = TeachersDto.builder()
                .id(1)
                .firstname("Husanboy")
                .lastname("Jorayev")
                .build();

        when(this.teachersMapper.toDto(any()))
                .thenReturn(teachersDto);
        when(this.teachersRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(teachers));

        ResponseDto<TeachersDto> group = this.teachersService.update(teachersDto, any());
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertNotNull(group.getData());
        Assertions.assertTrue(group.isSuccess());

        verify(this.teachersRepository, times(1)).findByIdAndDeletedAtIsNull(any());
        verify(this.teachersRepository, times(1)).save(any());
        verify(this.teachersMapper, times(1)).toDto(any());

    }

    @Test
    void updateNegative() {
        TeachersDto teachersDto = new TeachersDto();

        when(this.teachersRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<TeachersDto> group = this.teachersService.update(teachersDto, any());
        Assertions.assertFalse(group.isSuccess());
        Assertions.assertEquals(group.getCode(), -1);
        Assertions.assertNull(group.getData());

        verify(this.teachersRepository, times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void deletePositive() {
        Teachers teachers = Teachers.builder()
                .id(1)
                .firstname("Husanboy")
                .lastname("Jorayev")
                .build();

        TeachersDto teachersDto = TeachersDto.builder()
                .id(1)
                .firstname("Husanboy")
                .lastname("Jorayev")
                .build();

        when(this.teachersRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(teachers));

        when(this.teachersMapper.toDto(any()))
                .thenReturn(teachersDto);

        ResponseDto<TeachersDto> group = this.teachersService.delete(any());
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertTrue(group.isSuccess());
        Assertions.assertNotNull(group.getData());

        verify(this.teachersRepository, times(1)).save(any());
        verify(this.teachersRepository, times(1)).findByIdAndDeletedAtIsNull(any());
        verify(this.teachersMapper, times(1)).toDto(any());
    }

    @Test
    void deleteNegative() {

        when(this.teachersRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<TeachersDto> group = this.teachersService.delete(any());
        Assertions.assertFalse(group.isSuccess());
        Assertions.assertNull(group.getData());
        Assertions.assertEquals(group.getCode(), -1);

        verify(this.teachersRepository, times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void getAllPositive() {
        Teachers teachers = Teachers.builder()
                .id(1)
                .firstname("Husanboy")
                .lastname("Jorayev")
                .build();

        TeachersDto teachersDto = TeachersDto.builder()
                .id(1)
                .firstname("Husanboy")
                .lastname("Jorayev")
                .build();

        when(this.teachersRepository.findAllElements())
                .thenReturn(List.of(teachers));
        when(this.teachersMapper.toDto(any()))
                .thenReturn(teachersDto);

        ResponseDto<List<TeachersDto>> group = this.teachersService.getAll();
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertNotNull(group.getData());
        Assertions.assertTrue(group.isSuccess());


        verify(this.teachersRepository, times(1)).findAllElements();
        verify(this.teachersMapper, times(1)).toDto(any());
    }

    @Test
    void getAllNegative() {
        when(this.teachersRepository.findAllElements())
                .thenReturn(List.of());

        ResponseDto<List<TeachersDto>> group = this.teachersService.getAll();
        Assertions.assertFalse(group.isSuccess());
        Assertions.assertNull(group.getData());
        Assertions.assertEquals(group.getCode(), -1);

        verify(this.teachersRepository, times(1)).findAllElements();
    }
}
