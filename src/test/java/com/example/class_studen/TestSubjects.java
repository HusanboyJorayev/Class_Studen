package com.example.class_studen;


import com.example.class_studen.dto.ResponseDto;
import com.example.class_studen.dto.SubjectsDto;
import com.example.class_studen.model.Subjects;
import com.example.class_studen.repository.SubjectsRepository;
import com.example.class_studen.service.SubjectsService;
import com.example.class_studen.service.mapper.SubjectsMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestSubjects {

    private SubjectsRepository subjectsRepository;
    private SubjectsMapper subjectsMapper;
    private SubjectsService subjectsService;


    @BeforeEach
    void initObject() {
        this.subjectsMapper = Mockito.mock(SubjectsMapper.class);
        this.subjectsRepository = Mockito.mock(SubjectsRepository.class);
        this.subjectsService = new SubjectsService(subjectsRepository, subjectsMapper);
    }

    @Test
    void createPositive() {
        Subjects subjects = Subjects.builder()
                .id(1)
                .title("AMth")
                .build();

        SubjectsDto subjectsDto = SubjectsDto.builder()
                .id(1)
                .title("AMth")
                .build();
        when(this.subjectsMapper.toEntity(any()))
                .thenReturn(subjects);
        when(this.subjectsMapper.toDto(any()))
                .thenReturn(subjectsDto);

        ResponseDto<SubjectsDto> mark = this.subjectsService.create(any());
        Assertions.assertEquals(mark.getCode(), 0);
        Assertions.assertNotNull(mark.getData());
        Assertions.assertEquals(mark.getData().getId(), 1);

        verify(this.subjectsMapper, times(1)).toEntity(any());
        verify(this.subjectsMapper, times(1)).toDto(any());
        verify(this.subjectsRepository, times(1)).save(any());
    }

    @Test
    void getPositive() {
        Subjects subjects = Subjects.builder()
                .id(1)
                .title("AMth")
                .build();

        SubjectsDto subjectsDto = SubjectsDto.builder()
                .id(1)
                .title("AMth")
                .build();

        when(this.subjectsMapper.toDto(any()))
                .thenReturn(subjectsDto);

        when(this.subjectsRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(subjects));

        ResponseDto<SubjectsDto> group = this.subjectsService.get(any());
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertNotNull(group.getData());
        Assertions.assertEquals(group.getData().getId(), 1);

        verify(this.subjectsMapper, times(1)).toDto(any());
        verify(this.subjectsRepository, times(1)).findByIdAndDeletedAtIsNull(any());

    }

    @Test
    void getNegative() {
        when(this.subjectsRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<SubjectsDto> groups = this.subjectsService.get(any());
        Assertions.assertEquals(groups.getCode(), -1);
        Assertions.assertNull(groups.getData());
        Assertions.assertFalse(groups.isSuccess());

        verify(this.subjectsRepository, times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void updatePositive() {
        Subjects subjects = Subjects.builder()
                .id(1)
                .title("AMth")
                .build();

        SubjectsDto subjectsDto = SubjectsDto.builder()
                .id(1)
                .title("AMth")
                .build();

        when(this.subjectsMapper.toDto(any()))
                .thenReturn(subjectsDto);
        when(this.subjectsRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(subjects));

        ResponseDto<SubjectsDto> group = this.subjectsService.update(subjectsDto, any());
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertNotNull(group.getData());
        Assertions.assertTrue(group.isSuccess());

        verify(this.subjectsRepository, times(1)).findByIdAndDeletedAtIsNull(any());
        verify(this.subjectsRepository, times(1)).save(any());
        verify(this.subjectsMapper, times(1)).toDto(any());

    }

    @Test
    void updateNegative() {
        SubjectsDto subjectsDto = new SubjectsDto();

        when(this.subjectsRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<SubjectsDto> group = this.subjectsService.update(subjectsDto, any());
        Assertions.assertFalse(group.isSuccess());
        Assertions.assertEquals(group.getCode(), -1);
        Assertions.assertNull(group.getData());

        verify(this.subjectsRepository, times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void deletePositive() {
        Subjects subjects = Subjects.builder()
                .id(1)
                .title("AMth")
                .build();

        SubjectsDto subjectsDto = SubjectsDto.builder()
                .id(1)
                .title("AMth")
                .build();

        when(this.subjectsRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(subjects));

        when(this.subjectsMapper.toDto(any()))
                .thenReturn(subjectsDto);

        ResponseDto<SubjectsDto> group = this.subjectsService.delete(any());
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertTrue(group.isSuccess());
        Assertions.assertNotNull(group.getData());

        verify(this.subjectsRepository, times(1)).save(any());
        verify(this.subjectsRepository, times(1)).findByIdAndDeletedAtIsNull(any());
        verify(this.subjectsMapper, times(1)).toDto(any());
    }

    @Test
    void deleteNegative() {

        when(this.subjectsRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<SubjectsDto> group = this.subjectsService.delete(any());
        Assertions.assertFalse(group.isSuccess());
        Assertions.assertNull(group.getData());
        Assertions.assertEquals(group.getCode(), -1);

        verify(this.subjectsRepository, times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void getAllPositive() {
        Subjects subjects = Subjects.builder()
                .id(1)
                .title("AMth")
                .build();

        SubjectsDto subjectsDto = SubjectsDto.builder()
                .id(1)
                .title("AMth")
                .build();

        when(this.subjectsRepository.findAllElements())
                .thenReturn(List.of(subjects));
        when(this.subjectsMapper.toDto(any()))
                .thenReturn(subjectsDto);

        ResponseDto<List<SubjectsDto>> group = this.subjectsService.getAll();
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertNotNull(group.getData());
        Assertions.assertTrue(group.isSuccess());


        verify(this.subjectsRepository, times(1)).findAllElements();
        verify(this.subjectsMapper, times(1)).toDto(any());
    }

    @Test
    void getAllNegative() {
        when(this.subjectsRepository.findAllElements())
                .thenReturn(List.of());

        ResponseDto<List<SubjectsDto>> group = this.subjectsService.getAll();
        Assertions.assertFalse(group.isSuccess());
        Assertions.assertNull(group.getData());
        Assertions.assertEquals(group.getCode(), -1);

        verify(this.subjectsRepository, times(1)).findAllElements();
    }
}
