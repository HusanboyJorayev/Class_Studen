package com.example.class_studen;

import com.example.class_studen.service.mapper.StudentsMapper;
import com.example.class_studen.repository.StudentsRepository;
import com.example.class_studen.service.StudentsService;
import com.example.class_studen.dto.ResponseDto;
import com.example.class_studen.dto.StudentsDto;
import com.example.class_studen.model.Students;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.List;


public class TestStudents {
    private StudentsRepository studentsRepository;
    private StudentsMapper studentsMapper;
    private StudentsService studentsService;

    @BeforeEach
    void initObject() {
        this.studentsMapper = Mockito.mock(StudentsMapper.class);
        this.studentsRepository = Mockito.mock(StudentsRepository.class);
        this.studentsService = new StudentsService(studentsRepository, studentsMapper);
    }

    @Test
    void createPositive() {
        Students students = Students.builder()
                .id(1)
                .firstname("Husanboy")
                .lastname("Jorayev")
                .build();

        StudentsDto studentsDto = StudentsDto.builder()
                .id(1)
                .firstname("Husanboy")
                .lastname("Jorayev")
                .build();
        when(this.studentsMapper.toEntity(any()))
                .thenReturn(students);
        when(this.studentsMapper.toDto(any()))
                .thenReturn(studentsDto);

        ResponseDto<StudentsDto> mark = this.studentsService.create(any());
        Assertions.assertEquals(mark.getCode(), 0);
        Assertions.assertNotNull(mark.getData());
        Assertions.assertEquals(mark.getData().getId(), 1);

        verify(this.studentsMapper, times(1)).toEntity(any());
        verify(this.studentsMapper, times(1)).toDto(any());
        verify(this.studentsRepository, times(1)).save(any());
    }

    @Test
    void getPositive() {
        Students students = Students.builder()
                .id(1)
                .firstname("Husanboy")
                .lastname("Jorayev")
                .build();

        StudentsDto studentsDto = StudentsDto.builder()
                .id(1)
                .firstname("Husanboy")
                .lastname("Jorayev")
                .build();

        when(this.studentsMapper.toDto(any()))
                .thenReturn(studentsDto);

        when(this.studentsRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(students));

        ResponseDto<StudentsDto> group = this.studentsService.get(any());
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertNotNull(group.getData());
        Assertions.assertEquals(group.getData().getId(), 1);

        verify(this.studentsMapper, times(1)).toDto(any());
        verify(this.studentsRepository, times(1)).findByIdAndDeletedAtIsNull(any());

    }

    @Test
    void getNegative() {
        when(this.studentsRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<StudentsDto> groups = this.studentsService.get(any());
        Assertions.assertEquals(groups.getCode(), -1);
        Assertions.assertNull(groups.getData());
        Assertions.assertFalse(groups.isSuccess());

        verify(this.studentsRepository, times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void updatePositive() {
        Students students = Students.builder()
                .id(1)
                .firstname("Husanboy")
                .lastname("Jorayev")
                .build();

        StudentsDto studentsDto = StudentsDto.builder()
                .id(1)
                .firstname("Husanboy")
                .lastname("Jorayev")
                .build();

        when(this.studentsMapper.toDto(any()))
                .thenReturn(studentsDto);
        when(this.studentsRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(students));

        ResponseDto<StudentsDto> group = this.studentsService.update(studentsDto, any());
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertNotNull(group.getData());
        Assertions.assertTrue(group.isSuccess());

        verify(this.studentsRepository, times(1)).findByIdAndDeletedAtIsNull(any());
        verify(this.studentsRepository, times(1)).save(any());
        verify(this.studentsMapper, times(1)).toDto(any());

    }

    @Test
    void updateNegative() {
        StudentsDto studentsDto = new StudentsDto();

        when(this.studentsRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<StudentsDto> group = this.studentsService.update(studentsDto, any());
        Assertions.assertFalse(group.isSuccess());
        Assertions.assertEquals(group.getCode(), -1);
        Assertions.assertNull(group.getData());

        verify(this.studentsRepository, times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void deletePositive() {
        Students students = Students.builder()
                .id(1)
                .firstname("Husanboy")
                .lastname("Jorayev")
                .build();

        StudentsDto studentsDto = StudentsDto.builder()
                .id(1)
                .firstname("Husanboy")
                .lastname("Jorayev")
                .build();

        when(this.studentsRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(students));

        when(this.studentsMapper.toDto(any()))
                .thenReturn(studentsDto);

        ResponseDto<StudentsDto> group = this.studentsService.delete(any());
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertTrue(group.isSuccess());
        Assertions.assertNotNull(group.getData());

        verify(this.studentsRepository, times(1)).save(any());
        verify(this.studentsRepository, times(1)).findByIdAndDeletedAtIsNull(any());
        verify(this.studentsMapper, times(1)).toDto(any());
    }

    @Test
    void deleteNegative() {

        when(this.studentsRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<StudentsDto> group = this.studentsService.delete(any());
        Assertions.assertFalse(group.isSuccess());
        Assertions.assertNull(group.getData());
        Assertions.assertEquals(group.getCode(), -1);

        verify(this.studentsRepository, times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void getAllPositive() {
        Students students = Students.builder()
                .id(1)
                .firstname("Husanboy")
                .lastname("Jorayev")
                .build();

        StudentsDto studentsDto = StudentsDto.builder()
                .id(1)
                .firstname("Husanboy")
                .lastname("Jorayev")
                .build();

        when(this.studentsRepository.findAllElements())
                .thenReturn(List.of(students));
        when(this.studentsMapper.toDto(any()))
                .thenReturn(studentsDto);

        ResponseDto<List<StudentsDto>> group = this.studentsService.getAll();
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertNotNull(group.getData());
        Assertions.assertTrue(group.isSuccess());


        verify(this.studentsRepository, times(1)).findAllElements();
        verify(this.studentsMapper, times(1)).toDto(any());
    }

    @Test
    void getAllNegative() {
        when(this.studentsRepository.findAllElements())
                .thenReturn(List.of());

        ResponseDto<List<StudentsDto>> group = this.studentsService.getAll();
        Assertions.assertFalse(group.isSuccess());
        Assertions.assertNull(group.getData());
        Assertions.assertEquals(group.getCode(), -1);

        verify(this.studentsRepository, times(1)).findAllElements();
    }
}
