package com.example.class_studen;

import com.example.class_studen.dto.MarksDto;
import com.example.class_studen.dto.ResponseDto;
import com.example.class_studen.model.Marks;
import com.example.class_studen.repository.MarksRepository;
import com.example.class_studen.service.MarksService;
import com.example.class_studen.service.mapper.MarksMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestMarks {

    private MarksMapper marksMapper;
    private MarksRepository marksRepository;
    private MarksService marksService;


    @BeforeEach
    void initObject() {
        this.marksMapper = Mockito.mock(MarksMapper.class);
        this.marksRepository = Mockito.mock(MarksRepository.class);
        this.marksService = new MarksService(marksRepository, marksMapper);
    }

    @Test
    void createPositive() {
        Marks marks = Marks.builder()
                .id(1)
                .studentId(1)
                .build();

        MarksDto marksDto = MarksDto.builder()
                .id(1)
                .studentId(1)
                .build();
        when(this.marksMapper.toEntity(any()))
                .thenReturn(marks);
        when(this.marksMapper.toDto(any()))
                .thenReturn(marksDto);

        ResponseDto<MarksDto> mark = this.marksService.create(any());
        Assertions.assertEquals(mark.getCode(), 0);
        Assertions.assertNotNull(mark.getData());
        Assertions.assertEquals(mark.getData().getId(), 1);

        verify(this.marksMapper, times(1)).toEntity(any());
        verify(this.marksMapper, times(1)).toDto(any());
        verify(this.marksRepository, times(1)).save(any());
    }

    @Test
    void getPositive() {
        Marks marks = Marks.builder()
                .id(1)
                .studentId(1)
                .build();

        MarksDto marksDto = MarksDto.builder()
                .id(1)
                .studentId(1)
                .build();

        when(this.marksMapper.toDto(any()))
                .thenReturn(marksDto);

        when(this.marksRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(marks));

        ResponseDto<MarksDto> group = this.marksService.get(any());
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertNotNull(group.getData());
        Assertions.assertEquals(group.getData().getId(), 1);

        verify(this.marksMapper, times(1)).toDto(any());
        verify(this.marksRepository, times(1)).findByIdAndDeletedAtIsNull(any());

    }

    @Test
    void getNegative() {
        when(this.marksRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<MarksDto> groups = this.marksService.get(any());
        Assertions.assertEquals(groups.getCode(), -1);
        Assertions.assertNull(groups.getData());
        Assertions.assertFalse(groups.isSuccess());

        verify(this.marksRepository, times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void updatePositive() {
        Marks marks = Marks.builder()
                .id(1)
                .studentId(1)
                .build();

        MarksDto marksDto = MarksDto.builder()
                .id(1)
                .studentId(1)
                .build();

        when(this.marksMapper.toDto(any()))
                .thenReturn(marksDto);
        when(this.marksRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(marks));

        ResponseDto<MarksDto> group = this.marksService.update(marksDto, any());
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertNotNull(group.getData());
        Assertions.assertTrue(group.isSuccess());

        verify(this.marksRepository, times(1)).findByIdAndDeletedAtIsNull(any());
        verify(this.marksRepository, times(1)).save(any());
        verify(this.marksMapper, times(1)).toDto(any());

    }

    @Test
    void updateNegative() {
        MarksDto marksDto = new MarksDto();

        when(this.marksRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<MarksDto> group = this.marksService.update(marksDto, any());
        Assertions.assertFalse(group.isSuccess());
        Assertions.assertEquals(group.getCode(), -1);
        Assertions.assertNull(group.getData());

        verify(this.marksRepository, times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void deletePositive() {
        Marks marks = Marks.builder()
                .id(1)
                .studentId(1)
                .build();

        MarksDto marksDto = MarksDto.builder()
                .id(1)
                .studentId(1)
                .build();

        when(this.marksRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(marks));

        when(this.marksMapper.toDto(any()))
                .thenReturn(marksDto);

        ResponseDto<MarksDto> group = this.marksService.delete(any());
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertTrue(group.isSuccess());
        Assertions.assertNotNull(group.getData());

        verify(this.marksRepository, times(1)).save(any());
        verify(this.marksRepository, times(1)).findByIdAndDeletedAtIsNull(any());
        verify(this.marksMapper, times(1)).toDto(any());
    }

    @Test
    void deleteNegative() {

        when(this.marksRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<MarksDto> group = this.marksService.delete(any());
        Assertions.assertFalse(group.isSuccess());
        Assertions.assertNull(group.getData());
        Assertions.assertEquals(group.getCode(), -1);

        verify(this.marksRepository, times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void getAllPositive() {
        Marks marks = Marks.builder()
                .id(1)
                .studentId(1)
                .build();

        MarksDto marksDto = MarksDto.builder()
                .id(1)
                .studentId(1)
                .build();

        when(this.marksRepository.findAllElements())
                .thenReturn(List.of(marks));
        when(this.marksMapper.toDto(any()))
                .thenReturn(marksDto);

        ResponseDto<List<MarksDto>> group = this.marksService.getAll();
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertNotNull(group.getData());
        Assertions.assertTrue(group.isSuccess());


        verify(this.marksRepository, times(1)).findAllElements();
        verify(this.marksMapper, times(1)).toDto(any());
    }

    @Test
    void getAllNegative() {
        when(this.marksRepository.findAllElements())
                .thenReturn(List.of());

        ResponseDto<List<MarksDto>> group = this.marksService.getAll();
        Assertions.assertFalse(group.isSuccess());
        Assertions.assertNull(group.getData());
        Assertions.assertEquals(group.getCode(), -1);

        verify(this.marksRepository, times(1)).findAllElements();
    }
}
