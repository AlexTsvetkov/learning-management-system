package com.mentoring.lms.controller;

import com.mentoring.lms.dto.StudentDto;
import com.mentoring.lms.entity.Student;
import com.mentoring.lms.mapper.LmsMapper;
import com.mentoring.lms.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final LmsMapper mapper;

    @PostMapping
    public ResponseEntity<StudentDto> create(@Valid @RequestBody StudentDto dto) {
        Student saved = studentService.create(mapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> get(@PathVariable Long id) {
        return studentService.findById(id)
              .map(s -> ResponseEntity.ok(mapper.toDto(s)))
              .orElse(ResponseEntity.notFound().build());
    }
}
