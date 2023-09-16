package com.example.class_studen.controller;

import com.example.class_studen.dto.ResponseDto;
import com.example.class_studen.dto.SimpleCrud;
import com.example.class_studen.dto.TeachersDto;
import com.example.class_studen.service.TeachersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "teachers")
public class TeachersController implements SimpleCrud<Integer, TeachersDto> {
    private final TeachersService teachersService;

    @Override
    @PostMapping(value = "/create")
    public ResponseDto<TeachersDto> create(@RequestBody TeachersDto dto) {
        return this.teachersService.create(dto);
    }

    @Override
    @GetMapping(value = "/get")
    public ResponseDto<TeachersDto> get(@RequestParam Integer id) {
        return this.teachersService.get(id);
    }

    @Override
    @PutMapping(value = "/update")
    public ResponseDto<TeachersDto> update(@RequestBody TeachersDto dto, @RequestParam Integer id) {
        return this.teachersService.update(dto, id);
    }

    @Override
    @DeleteMapping(value = "/delete")
    public ResponseDto<TeachersDto> delete(@RequestParam Integer id) {
        return this.teachersService.delete(id);
    }

    @Override
    @GetMapping(value = "/getAll")
    public ResponseDto<List<TeachersDto>> getAll() {
        return this.teachersService.getAll();
    }
}
