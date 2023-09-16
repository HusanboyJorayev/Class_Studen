package com.example.class_studen.controller;

import com.example.class_studen.dto.ResponseDto;
import com.example.class_studen.dto.SimpleCrud;
import com.example.class_studen.dto.SubjTeachDto;
import com.example.class_studen.service.SubTeachService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "subTeach")
public class SubjTeachController implements SimpleCrud<Integer, SubjTeachDto> {
    private final SubTeachService subTeachService;

    @Override
    @PostMapping(value = "/create")
    public ResponseDto<SubjTeachDto> create(@RequestBody SubjTeachDto dto) {
        return this.subTeachService.create(dto);
    }

    @Override
    @GetMapping(value = "/get")
    public ResponseDto<SubjTeachDto> get(@RequestParam Integer id) {
        return this.subTeachService.get(id);
    }

    @Override
    @PutMapping(value = "/update")
    public ResponseDto<SubjTeachDto> update(@RequestBody SubjTeachDto dto, @RequestParam Integer id) {
        return this.subTeachService.update(dto, id);
    }

    @Override
    @DeleteMapping(value = "/delete")
    public ResponseDto<SubjTeachDto> delete(@RequestParam Integer id) {
        return this.subTeachService.delete(id);
    }

    @Override
    @GetMapping(value = "/getAll")
    public ResponseDto<List<SubjTeachDto>> getAll() {
        return this.subTeachService.getAll();
    }
}
