package com.example.class_studen;

import com.example.class_studen.dto.MarksDto;
import com.example.class_studen.dto.ResponseDto;
import com.example.class_studen.dto.SubjTeachDto;
import com.example.class_studen.model.Marks;
import com.example.class_studen.model.SubTeach;
import com.example.class_studen.repository.SubTeachRepository;
import com.example.class_studen.service.SubTeachService;
import com.example.class_studen.service.mapper.SubTeachMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestSubTeach {

    private SubTeachMapper subTeachMapper;
    private SubTeachRepository subTeachRepository;
    private SubTeachService subTeachService;

    @BeforeEach
    void initObject() {
        this.subTeachMapper = Mockito.mock(SubTeachMapper.class);
        this.subTeachRepository = Mockito.mock(SubTeachRepository.class);
        this.subTeachService = new SubTeachService(subTeachRepository, subTeachMapper);
    }

    @Test
    void createPositive() {
        SubTeach subTeach = SubTeach.builder()
                .id(1)
                .build();

        SubjTeachDto subjTeachDto = SubjTeachDto.builder()
                .id(1)
                .build();
        when(this.subTeachMapper.toEntity(any()))
                .thenReturn(subTeach);
        when(this.subTeachMapper.toDto(any()))
                .thenReturn(subjTeachDto);

        ResponseDto<SubjTeachDto> mark = this.subTeachService.create(any());
        Assertions.assertEquals(mark.getCode(), 0);
        Assertions.assertNotNull(mark.getData());
        Assertions.assertEquals(mark.getData().getId(), 1);

        verify(this.subTeachMapper, times(1)).toEntity(any());
        verify(this.subTeachMapper, times(1)).toDto(any());
        verify(this.subTeachRepository, times(1)).save(any());
    }

    @Test
    void getPositive() {
        SubTeach subTeach = SubTeach.builder()
                .id(1)
                .build();

        SubjTeachDto subjTeachDto = SubjTeachDto.builder()
                .id(1)
                .build();

        when(this.subTeachMapper.toDto(any()))
                .thenReturn(subjTeachDto);

        when(this.subTeachRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(subTeach));

        ResponseDto<SubjTeachDto> group = this.subTeachService.get(any());
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertNotNull(group.getData());
        Assertions.assertEquals(group.getData().getId(), 1);

        verify(this.subTeachMapper, times(1)).toDto(any());
        verify(this.subTeachRepository, times(1)).findByIdAndDeletedAtIsNull(any());

    }

    @Test
    void getNegative() {
        when(this.subTeachRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<SubjTeachDto> groups = this.subTeachService.get(any());
        Assertions.assertEquals(groups.getCode(), -1);
        Assertions.assertNull(groups.getData());
        Assertions.assertFalse(groups.isSuccess());

        verify(this.subTeachRepository, times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void updatePositive() {
        SubTeach subTeach = SubTeach.builder()
                .id(1)
                .build();

        SubjTeachDto subjTeachDto = SubjTeachDto.builder()
                .id(1)
                .build();

        when(this.subTeachMapper.toDto(any()))
                .thenReturn(subjTeachDto);
        when(this.subTeachRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(subTeach));

        ResponseDto<SubjTeachDto> group = this.subTeachService.update(subjTeachDto, any());
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertNotNull(group.getData());
        Assertions.assertTrue(group.isSuccess());

        verify(this.subTeachRepository, times(1)).findByIdAndDeletedAtIsNull(any());
        verify(this.subTeachRepository, times(1)).save(any());
        verify(this.subTeachMapper, times(1)).toDto(any());

    }

    @Test
    void updateNegative() {
        SubjTeachDto subjTeachDto = new SubjTeachDto();

        when(this.subTeachRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<SubjTeachDto> group = this.subTeachService.update(subjTeachDto, any());
        Assertions.assertFalse(group.isSuccess());
        Assertions.assertEquals(group.getCode(), -1);
        Assertions.assertNull(group.getData());

        verify(this.subTeachRepository, times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void deletePositive() {
        SubTeach subTeach = SubTeach.builder()
                .id(1)
                .build();

        SubjTeachDto subjTeachDto = SubjTeachDto.builder()
                .id(1)
                .build();

        when(this.subTeachRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.ofNullable(subTeach));

        when(this.subTeachMapper.toDto(any()))
                .thenReturn(subjTeachDto);

        ResponseDto<SubjTeachDto> group = this.subTeachService.delete(any());
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertTrue(group.isSuccess());
        Assertions.assertNotNull(group.getData());

        verify(this.subTeachRepository, times(1)).save(any());
        verify(this.subTeachRepository, times(1)).findByIdAndDeletedAtIsNull(any());
        verify(this.subTeachMapper, times(1)).toDto(any());
    }

    @Test
    void deleteNegative() {

        when(this.subTeachRepository.findByIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<SubjTeachDto> group = this.subTeachService.delete(any());
        Assertions.assertFalse(group.isSuccess());
        Assertions.assertNull(group.getData());
        Assertions.assertEquals(group.getCode(), -1);

        verify(this.subTeachRepository, times(1)).findByIdAndDeletedAtIsNull(any());
    }

    @Test
    void getAllPositive() {
        SubTeach subTeach = SubTeach.builder()
                .id(1)
                .build();

        SubjTeachDto subjTeachDto = SubjTeachDto.builder()
                .id(1)
                .build();

        when(this.subTeachRepository.findAllElements())
                .thenReturn(List.of(subTeach));
        when(this.subTeachMapper.toDto(any()))
                .thenReturn(subjTeachDto);

        ResponseDto<List<SubjTeachDto>> group = this.subTeachService.getAll();
        Assertions.assertEquals(group.getCode(), 0);
        Assertions.assertNotNull(group.getData());
        Assertions.assertTrue(group.isSuccess());


        verify(this.subTeachRepository, times(1)).findAllElements();
        verify(this.subTeachMapper, times(1)).toDto(any());
    }

    @Test
    void getAllNegative() {
        when(this.subTeachRepository.findAllElements())
                .thenReturn(List.of());

        ResponseDto<List<SubjTeachDto>> group = this.subTeachService.getAll();
        Assertions.assertFalse(group.isSuccess());
        Assertions.assertNull(group.getData());
        Assertions.assertEquals(group.getCode(), -1);

        verify(this.subTeachRepository, times(1)).findAllElements();
    }
}
