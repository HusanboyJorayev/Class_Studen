package com.example.class_studen.service;


import com.example.class_studen.dto.ResponseDto;
import com.example.class_studen.dto.SimpleCrud;
import com.example.class_studen.dto.SubjectsDto;
import com.example.class_studen.model.Subjects;
import com.example.class_studen.repository.SubjectsRepository;
import com.example.class_studen.service.mapper.SubjectsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectsService implements SimpleCrud<Integer, SubjectsDto> {
    private final SubjectsRepository subjectsRepository;
    private final SubjectsMapper subjectsMapper;

    @Override
    public ResponseDto<SubjectsDto> create(SubjectsDto dto) {
        Subjects subjects = this.subjectsMapper.toEntity(dto);
        subjects.setCreatedAt(LocalDateTime.now());
        this.subjectsRepository.save(subjects);

        return ResponseDto.<SubjectsDto>builder()
                .success(true)
                .message("Ok")
                .data(this.subjectsMapper.toDto(subjects))
                .build();
    }

    @Override
    public ResponseDto<SubjectsDto> get(Integer id) {
        return this.subjectsRepository.findByIdAndDeletedAtIsNull(id)
                .map(marks -> ResponseDto.<SubjectsDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.subjectsMapper.toDto(marks))
                        .build())
                .orElse(ResponseDto.<SubjectsDto>builder()
                        .code(-1)
                        .message("Subject is not found")
                        .build());
    }

    @Override
    public ResponseDto<SubjectsDto> update(SubjectsDto dto, Integer id) {
        return this.subjectsRepository.findByIdAndDeletedAtIsNull(id)
                .map(subjects -> {
                    subjects.setUpdatedAt(LocalDateTime.now());
                    this.subjectsMapper.update(subjects, dto);
                    this.subjectsRepository.save(subjects);
                    return ResponseDto.<SubjectsDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.subjectsMapper.toDto(subjects))
                            .build();
                })
                .orElse(ResponseDto.<SubjectsDto>builder()
                        .code(-1)
                        .message("Subject is not found")
                        .build());
    }

    @Override
    public ResponseDto<SubjectsDto> delete(Integer id) {
        return this.subjectsRepository.findByIdAndDeletedAtIsNull(id)
                .map(subjects -> {
                    subjects.setDeletedAt(LocalDateTime.now());
                    this.subjectsRepository.save(subjects);
                    return ResponseDto.<SubjectsDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.subjectsMapper.toDto(subjects))
                            .build();
                })
                .orElse(ResponseDto.<SubjectsDto>builder()
                        .code(-1)
                        .message("Subject is not found")
                        .build());
    }

    @Override
    public ResponseDto<List<SubjectsDto>> getAll() {
        List<Subjects> subjects = this.subjectsRepository.findAllElements();
        if (subjects.isEmpty()) {
            return ResponseDto.<List<SubjectsDto>>builder()
                    .code(-1)
                    .message("Subjects are not found")
                    .build();
        }
        return ResponseDto.<List<SubjectsDto>>builder()
                .success(true)
                .message("Ok")
                .data(subjects.stream().map(this.subjectsMapper::toDto).toList())
                .build();
    }
}
