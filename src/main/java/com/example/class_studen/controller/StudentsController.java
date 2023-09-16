package com.example.class_studen.controller;

import com.example.class_studen.dto.ResponseDto;
import com.example.class_studen.dto.SimpleCrud;
import com.example.class_studen.dto.StudentsDto;
import com.example.class_studen.service.StudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "student")
public class StudentsController implements SimpleCrud<Integer, StudentsDto> {
    private final StudentsService studentsService;

    @Override
    @PostMapping(value = "/create")
    public ResponseDto<StudentsDto> create(@RequestBody StudentsDto dto) {
        return this.studentsService.create(dto);
    }

    @Override
    @GetMapping(value = "/get")
    public ResponseDto<StudentsDto> get(@RequestParam Integer id) {
        return this.studentsService.get(id);
    }

    @Override
    @PutMapping(value = "/update")
    public ResponseDto<StudentsDto> update(@RequestBody StudentsDto dto, @RequestParam Integer id) {
        return this.studentsService.update(dto, id);
    }

    @Override
    @DeleteMapping(value = "/delete")
    public ResponseDto<StudentsDto> delete(@RequestParam Integer id) {
        return this.studentsService.delete(id);
    }

    @Override
    @GetMapping(value = "/getAll")
    public ResponseDto<List<StudentsDto>> getAll() {
        return this.studentsService.getAll();
    }
}
