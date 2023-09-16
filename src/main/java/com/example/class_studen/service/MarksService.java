package com.example.class_studen.service;


import com.example.class_studen.dto.MarksDto;
import com.example.class_studen.dto.ResponseDto;
import com.example.class_studen.dto.SimpleCrud;
import com.example.class_studen.model.Marks;
import com.example.class_studen.repository.MarksRepository;
import com.example.class_studen.service.mapper.MarksMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarksService implements SimpleCrud<Integer, MarksDto> {
    private final MarksRepository marksRepository;
    private final MarksMapper marksMapper;

    @Override
    public ResponseDto<MarksDto> create(MarksDto dto) {
        Marks marks = this.marksMapper.toEntity(dto);
        marks.setCreatedAt(LocalDateTime.now());
        this.marksRepository.save(marks);

        return ResponseDto.<MarksDto>builder()
                .success(true)
                .message("Ok")
                .data(this.marksMapper.toDto(marks))
                .build();
    }

    @Override
    public ResponseDto<MarksDto> get(Integer id) {
        return this.marksRepository.findByIdAndDeletedAtIsNull(id)
                .map(marks -> ResponseDto.<MarksDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.marksMapper.toDto(marks))
                        .build())
                .orElse(ResponseDto.<MarksDto>builder()
                        .code(-1)
                        .message("Mark is not found")
                        .build());
    }

    @Override
    public ResponseDto<MarksDto> update(MarksDto dto, Integer id) {
        return this.marksRepository.findByIdAndDeletedAtIsNull(id)
                .map(marks -> {
                    marks.setUpdatedAt(LocalDateTime.now());
                    this.marksMapper.update(marks, dto);
                    this.marksRepository.save(marks);
                    return ResponseDto.<MarksDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.marksMapper.toDto(marks))
                            .build();
                })
                .orElse(ResponseDto.<MarksDto>builder()
                        .code(-1)
                        .message("Mark is not found")
                        .build());
    }

    @Override
    public ResponseDto<MarksDto> delete(Integer id) {
        return this.marksRepository.findByIdAndDeletedAtIsNull(id)
                .map(marks -> {
                    marks.setDeletedAt(LocalDateTime.now());
                    this.marksRepository.save(marks);
                    return ResponseDto.<MarksDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.marksMapper.toDto(marks))
                            .build();
                })
                .orElse(ResponseDto.<MarksDto>builder()
                        .code(-1)
                        .message("Mark is not found")
                        .build());
    }

    @Override
    public ResponseDto<List<MarksDto>> getAll() {
        List<Marks> marks = this.marksRepository.findAllElements();
        if (marks.isEmpty()) {
            return ResponseDto.<List<MarksDto>>builder()
                    .code(-1)
                    .message("Groups are not found")
                    .build();
        }
        return ResponseDto.<List<MarksDto>>builder()
                .success(true)
                .message("Ok")
                .data(marks.stream().map(this.marksMapper::toDto).toList())
                .build();
    }
}
