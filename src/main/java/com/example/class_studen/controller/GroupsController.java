package com.example.class_studen.controller;

import com.example.class_studen.dto.GroupsDto;
import com.example.class_studen.dto.ResponseDto;
import com.example.class_studen.dto.SimpleCrud;
import com.example.class_studen.service.GroupsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "groups")
public class GroupsController implements SimpleCrud<Integer, GroupsDto> {
    private final GroupsService groupsService;

    @Override
    @PostMapping(value = "/create")
    public ResponseDto<GroupsDto> create(@RequestBody GroupsDto dto) {
        return this.groupsService.create(dto);
    }

    @Override
    @GetMapping(value = "/get")
    public ResponseDto<GroupsDto> get(@RequestParam Integer id) {
        return this.groupsService.get(id);
    }

    @Override
    @PutMapping(value = "/update")
    public ResponseDto<GroupsDto> update(@RequestBody GroupsDto dto, @RequestParam Integer id) {
        return this.groupsService.update(dto, id);
    }

    @Override
    @DeleteMapping(value = "/delete")
    public ResponseDto<GroupsDto> delete(@RequestParam Integer id) {
        return this.groupsService.delete(id);
    }

    @Override
    @GetMapping(value = "/getAll")
    public ResponseDto<List<GroupsDto>> getAll() {
        return this.groupsService.getAll();
    }
}
