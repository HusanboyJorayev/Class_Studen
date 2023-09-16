package com.example.class_studen.service;

import com.example.class_studen.dto.GroupsDto;
import com.example.class_studen.dto.ResponseDto;
import com.example.class_studen.dto.SimpleCrud;
import com.example.class_studen.model.Groups;
import com.example.class_studen.repository.GroupsRepository;
import com.example.class_studen.service.mapper.GroupsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class GroupsService implements SimpleCrud<Integer, GroupsDto> {
    private final GroupsRepository groupsRepository;
    private final GroupsMapper groupsMapper;

    @Override
    public ResponseDto<GroupsDto> create(GroupsDto dto) {
        Groups groups = this.groupsMapper.toEntity(dto);
        groups.setCreatedAt(LocalDateTime.now());
        this.groupsRepository.save(groups);

        return ResponseDto.<GroupsDto>builder()
                .success(true)
                .message("Ok")
                .data(this.groupsMapper.toDto(groups))
                .build();
    }

    @Override
    public ResponseDto<GroupsDto> get(Integer id) {

        return this.groupsRepository.findByIdAndDeletedAtIsNull(id)
                .map(groups -> ResponseDto.<GroupsDto>builder()
                        .success(true)
                        .message("Ok")
                        .data(this.groupsMapper.toDto(groups))
                        .build())
                .orElse(ResponseDto.<GroupsDto>builder()
                        .code(-1)
                        .message("Group is not found")
                        .build());
    }

    @Override
    public ResponseDto<GroupsDto> update(GroupsDto dto, Integer id) {

        return this.groupsRepository.findByIdAndDeletedAtIsNull(id)
                .map(groups -> {
                    groups.setUpdatedAt(LocalDateTime.now());
                    this.groupsMapper.update(groups, dto);
                    this.groupsRepository.save(groups);
                    return ResponseDto.<GroupsDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.groupsMapper.toDto(groups))
                            .build();
                })
                .orElse(ResponseDto.<GroupsDto>builder()
                        .code(-1)
                        .message("Group is not found")
                        .build());
    }

    @Override
    public ResponseDto<GroupsDto> delete(Integer id) {

        return this.groupsRepository.findByIdAndDeletedAtIsNull(id)
                .map(groups -> {
                    groups.setDeletedAt(LocalDateTime.now());
                    this.groupsRepository.save(groups);
                    return ResponseDto.<GroupsDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(this.groupsMapper.toDto(groups))
                            .build();
                })
                .orElse(ResponseDto.<GroupsDto>builder()
                        .code(-1)
                        .message("Group is not found")
                        .build());

    }

    @Override
    public ResponseDto<List<GroupsDto>> getAll() {
        List<Groups> groups = this.groupsRepository.findAllElements();
        if (groups.isEmpty()) {
            return ResponseDto.<List<GroupsDto>>builder()
                    .code(-1)
                    .message("Groups are not found")
                    .build();
        }
        return ResponseDto.<List<GroupsDto>>builder()
                .success(true)
                .message("Ok")
                .data(groups.stream().map(this.groupsMapper::toDto).toList())
                .build();
    }
}
