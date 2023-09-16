package com.example.class_studen.service;

import com.example.class_studen.dto.MarksDto;
import com.example.class_studen.dto.ResponseDto;
import com.example.class_studen.dto.SimpleCrud;
import com.example.class_studen.dto.TeachersDto;
import com.example.class_studen.model.Marks;
import com.example.class_studen.model.Teachers;
import com.example.class_studen.repository.TeachersRepository;
import com.example.class_studen.service.mapper.TeachersMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeachersService implements SimpleCrud<Integer, TeachersDto> {
    private final TeachersRepository teachersRepository;
    private final TeachersMapper teachersMapper;

    @Override
    public ResponseDto<TeachersDto> create(TeachersDto dto) {
        Teachers teacher = this.teachersMapper.toEntity(dto);
        teacher.setCreatedAt(LocalDateTime.now());
        this.teachersRepository.save(teacher);

        return ResponseDto.<TeachersDto>builder()
                .success(true)
                .message("Ok")
                .data(this.teachersMapper.toDto(teacher))
                .build();
    }

    @Override
    public ResponseDto<TeachersDto> get(Integer id) {
        return this.teachersRepository.findByIdAndDeletedAtIsNull(id)
                .map(teachers -> ResponseDto.<TeachersDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.teachersMapper.toDto(teachers))
                        .build())
                .orElse(ResponseDto.<TeachersDto>builder()
                        .code(-1)
                        .message("Teacher is not found")
                        .build());
    }

    @Override
    public ResponseDto<TeachersDto> update(TeachersDto dto, Integer id) {
        return this.teachersRepository.findByIdAndDeletedAtIsNull(id)
                .map(teachers -> {
                    teachers.setUpdatedAt(LocalDateTime.now());
                    this.teachersMapper.update(teachers, dto);
                    this.teachersRepository.save(teachers);
                    return ResponseDto.<TeachersDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.teachersMapper.toDto(teachers))
                            .build();
                })
                .orElse(ResponseDto.<TeachersDto>builder()
                        .code(-1)
                        .message("Teacher  is not found")
                        .build());
    }

    @Override
    public ResponseDto<TeachersDto> delete(Integer id) {
        return this.teachersRepository.findByIdAndDeletedAtIsNull(id)
                .map(teachers -> {
                    teachers.setDeletedAt(LocalDateTime.now());
                    this.teachersRepository.save(teachers);
                    return ResponseDto.<TeachersDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.teachersMapper.toDto(teachers))
                            .build();
                })
                .orElse(ResponseDto.<TeachersDto>builder()
                        .code(-1)
                        .message("Teacher is not found")
                        .build());
    }

    @Override
    public ResponseDto<List<TeachersDto>> getAll() {
        List<Teachers> teacher = this.teachersRepository.findAllElements();
        if (teacher.isEmpty()) {
            return ResponseDto.<List<TeachersDto>>builder()
                    .code(-1)
                    .message("Teachers are not found")
                    .build();
        }
        return ResponseDto.<List<TeachersDto>>builder()
                .success(true)
                .message("Ok")
                .data(teacher.stream().map(this.teachersMapper::toDto).toList())
                .build();
    }
}
