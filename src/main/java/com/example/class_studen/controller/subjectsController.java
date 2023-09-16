package com.example.class_studen.controller;

import com.example.class_studen.dto.ResponseDto;
import com.example.class_studen.dto.SimpleCrud;
import com.example.class_studen.dto.SubjectsDto;
import com.example.class_studen.service.SubjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "subjects")
public class subjectsController implements SimpleCrud<Integer, SubjectsDto> {
    private final SubjectsService subjectsService;

    @Override
    @PostMapping(value = "/create")
    public ResponseDto<SubjectsDto> create(@RequestBody SubjectsDto dto) {
        return this.subjectsService.create(dto);
    }

    @Override
    @GetMapping(value = "/get")
    public ResponseDto<SubjectsDto> get(@RequestParam Integer id) {
        return this.subjectsService.get(id);
    }

    @Override
    @PutMapping(value = "/update")
    public ResponseDto<SubjectsDto> update(@RequestBody SubjectsDto dto, @RequestParam Integer id) {
        return this.subjectsService.update(dto, id);
    }

    @Override
    @DeleteMapping(value = "/delete")
    public ResponseDto<SubjectsDto> delete(@RequestParam Integer id) {
        return this.subjectsService.delete(id);
    }

    @Override
    @GetMapping(value = "/getAll")
    public ResponseDto<List<SubjectsDto>> getAll() {
        return this.subjectsService.getAll();
    }
}
