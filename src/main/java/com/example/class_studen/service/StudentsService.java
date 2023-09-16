package com.example.class_studen.service;


import com.example.class_studen.dto.ResponseDto;
import com.example.class_studen.dto.SimpleCrud;
import com.example.class_studen.dto.StudentsDto;
import com.example.class_studen.model.Students;
import com.example.class_studen.repository.StudentsRepository;
import com.example.class_studen.service.mapper.StudentsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentsService implements SimpleCrud<Integer, StudentsDto> {
    private final StudentsRepository studentsRepository;
    private final StudentsMapper studentsMapper;

    @Override
    public ResponseDto<StudentsDto> create(StudentsDto dto) {
        Students students = this.studentsMapper.toEntity(dto);
        students.setCreatedAt(LocalDateTime.now());
        this.studentsRepository.save(students);

        return ResponseDto.<StudentsDto>builder()
                .success(true)
                .message("Ok")
                .data(this.studentsMapper.toDto(students))
                .build();
    }

    @Override
    public ResponseDto<StudentsDto> get(Integer id) {
        return this.studentsRepository.findByIdAndDeletedAtIsNull(id)
                .map(students -> ResponseDto.<StudentsDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.studentsMapper.toDto(students))
                        .build())
                .orElse(ResponseDto.<StudentsDto>builder()
                        .code(-1)
                        .message("Student is not found")
                        .build());
    }

    @Override
    public ResponseDto<StudentsDto> update(StudentsDto dto, Integer id) {
        return this.studentsRepository.findByIdAndDeletedAtIsNull(id)
                .map(students -> {
                    students.setUpdatedAt(LocalDateTime.now());
                    this.studentsMapper.update(students, dto);
                    this.studentsRepository.save(students);
                    return ResponseDto.<StudentsDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.studentsMapper.toDto(students))
                            .build();
                })
                .orElse(ResponseDto.<StudentsDto>builder()
                        .code(-1)
                        .message("Student is not found")
                        .build());
    }

    @Override
    public ResponseDto<StudentsDto> delete(Integer id) {
        return this.studentsRepository.findByIdAndDeletedAtIsNull(id)
                .map(students -> {
                    students.setDeletedAt(LocalDateTime.now());
                    this.studentsRepository.save(students);
                    return ResponseDto.<StudentsDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.studentsMapper.toDto(students))
                            .build();
                })
                .orElse(ResponseDto.<StudentsDto>builder()
                        .code(-1)
                        .message("Student is not found")
                        .build());
    }

    @Override
    public ResponseDto<List<StudentsDto>> getAll() {
        List<Students> students = this.studentsRepository.findAllElements();
        if (students.isEmpty()) {
            return ResponseDto.<List<StudentsDto>>builder()
                    .code(-1)
                    .message("Student are not found")
                    .build();
        }
        return ResponseDto.<List<StudentsDto>>builder()
                .success(true)
                .message("Ok")
                .data(students.stream().map(this.studentsMapper::toDto).toList())
                .build();
    }
}
