package com.mentoring.lms.mapper;

import com.mentoring.lms.dto.CourseDto;
import com.mentoring.lms.dto.StudentDto;
import com.mentoring.lms.entity.Course;
import com.mentoring.lms.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LmsMapper {
    StudentDto toDto(Student s);

    Student toEntity(StudentDto dto);

    CourseDto toDto(Course c);

    Course toEntity(CourseDto dto);
}
