package com.example.class_studen.service;

import com.example.class_studen.dto.MarksDto;
import com.example.class_studen.dto.ResponseDto;
import com.example.class_studen.dto.SimpleCrud;
import com.example.class_studen.dto.SubjTeachDto;
import com.example.class_studen.model.Marks;
import com.example.class_studen.model.SubTeach;
import com.example.class_studen.repository.SubTeachRepository;
import com.example.class_studen.service.mapper.SubTeachMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubTeachService implements SimpleCrud<Integer, SubjTeachDto> {
    private final SubTeachRepository subTeachRepository;
    private final SubTeachMapper subTeachMapper;

    @Override
    public ResponseDto<SubjTeachDto> create(SubjTeachDto dto) {
        SubTeach subTeach = this.subTeachMapper.toEntity(dto);
        subTeach.setCreatedAt(LocalDateTime.now());
        this.subTeachRepository.save(subTeach);

        return ResponseDto.<SubjTeachDto>builder()
                .success(true)
                .message("Ok")
                .data(this.subTeachMapper.toDto(subTeach))
                .build();
    }

    @Override
    public ResponseDto<SubjTeachDto> get(Integer id) {
        return this.subTeachRepository.findByIdAndDeletedAtIsNull(id)
                .map(subTeach -> ResponseDto.<SubjTeachDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.subTeachMapper.toDto(subTeach))
                        .build())
                .orElse(ResponseDto.<SubjTeachDto>builder()
                        .code(-1)
                        .message("SubTeach is not found")
                        .build());
    }

    @Override
    public ResponseDto<SubjTeachDto> update(SubjTeachDto dto, Integer id) {
        return this.subTeachRepository.findByIdAndDeletedAtIsNull(id)
                .map(subTeach -> {
                    subTeach.setUpdatedAt(LocalDateTime.now());
                    this.subTeachMapper.update(subTeach, dto);
                    this.subTeachRepository.save(subTeach);
                    return ResponseDto.<SubjTeachDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.subTeachMapper.toDto(subTeach))
                            .build();
                })
                .orElse(ResponseDto.<SubjTeachDto>builder()
                        .code(-1)
                        .message("SubTeach is not found")
                        .build());
    }

    @Override
    public ResponseDto<SubjTeachDto> delete(Integer id) {
        return this.subTeachRepository.findByIdAndDeletedAtIsNull(id)
                .map(subTeach -> {
                    subTeach.setDeletedAt(LocalDateTime.now());
                    this.subTeachRepository.save(subTeach);
                    return ResponseDto.<SubjTeachDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.subTeachMapper.toDto(subTeach))
                            .build();
                })
                .orElse(ResponseDto.<SubjTeachDto>builder()
                        .code(-1)
                        .message("SubTeach is not found")
                        .build());
    }

    @Override
    public ResponseDto<List<SubjTeachDto>> getAll() {
        List<SubTeach> subTeach = this.subTeachRepository.findAllElements();
        if (subTeach.isEmpty()) {
            return ResponseDto.<List<SubjTeachDto>>builder()
                    .code(-1)
                    .message("SubTeach are not found")
                    .build();
        }
        return ResponseDto.<List<SubjTeachDto>>builder()
                .success(true)
                .message("Ok")
                .data(subTeach.stream().map(this.subTeachMapper::toDto).toList())
                .build();
    }
}
