package com.mentoring.lms.controller;

import com.mentoring.lms.dto.StudentDto;
import com.mentoring.lms.entity.Student;
import com.mentoring.lms.mapper.LmsMapper;
import com.mentoring.lms.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Mock
    private StudentService studentService;
    @Mock
    private LmsMapper mapper;
    @InjectMocks
    private StudentController controller;

    @Test
    void create_ReturnsCreatedAndDto() {
        StudentDto inputDto = new StudentDto(1L, "Test Name", "test@email.com", 2L);
        Student mappedEntity = new Student();
        Student savedEntity = new Student();
        StudentDto outputDto = new StudentDto(1L, "Test Name", "test@email.com", 2L);

        when(mapper.toEntity(inputDto)).thenReturn(mappedEntity);
        when(studentService.create(mappedEntity)).thenReturn(savedEntity);
        when(mapper.toDto(savedEntity)).thenReturn(outputDto);

        ResponseEntity<StudentDto> response = controller.create(inputDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(outputDto, response.getBody());
        verify(mapper).toEntity(inputDto);
        verify(studentService).create(mappedEntity);
        verify(mapper).toDto(savedEntity);
    }

    @Test
    void get_Found_ReturnsOkAndDto() {
        Long id = 1L;
        Student entity = new Student();
        StudentDto dto = new StudentDto(1L, "Test Name", "test@email.com", 2L);;

        when(studentService.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        ResponseEntity<StudentDto> response = controller.get(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
        verify(studentService).findById(id);
        verify(mapper).toDto(entity);
    }

    @Test
    void get_NotFound_ReturnsNotFound() {
        Long id = 99L;
        when(studentService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<StudentDto> response = controller.get(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(studentService).findById(id);
        verifyNoInteractions(mapper);
    }
}

