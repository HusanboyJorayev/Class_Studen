package com.example.class_studen.controller;

import com.example.class_studen.dto.MarksDto;
import com.example.class_studen.dto.ResponseDto;
import com.example.class_studen.dto.SimpleCrud;
import com.example.class_studen.service.MarksService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "marks")
public class MarksController implements SimpleCrud<Integer, MarksDto> {
    private final MarksService marksService;

    @Override
    @PostMapping(value = "/create")
    public ResponseDto<MarksDto> create(@RequestBody MarksDto dto) {
        return this.marksService.create(dto);
    }

    @Override
    @GetMapping(value = "/get")
    public ResponseDto<MarksDto> get(@RequestParam Integer id) {
        return this.marksService.get(id);
    }

    @Override
    @PutMapping(value = "/update")
    public ResponseDto<MarksDto> update(@RequestBody MarksDto dto, @RequestParam Integer id) {
        return this.marksService.update(dto, id);
    }

    @Override
    @DeleteMapping(value = "/delete")
    public ResponseDto<MarksDto> delete(@RequestParam Integer id) {
        return this.marksService.delete(id);
    }

    @Override
    @GetMapping(value = "/getAll")
    public ResponseDto<List<MarksDto>> getAll() {
        return this.marksService.getAll();
    }
}
