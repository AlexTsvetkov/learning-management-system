package com.mentoring.lms.mapper;

import com.mentoring.lms.dto.CourseDto;
import com.mentoring.lms.dto.StudentDto;
import com.mentoring.lms.entity.Course;
import com.mentoring.lms.entity.Student;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-28T13:15:31+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (SAP SE)"
)
@Component
public class LmsMapperImpl implements LmsMapper {

    @Override
    public StudentDto toDto(Student s) {
        if ( s == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String email = null;
        Long coins = null;

        id = s.getId();
        name = s.getName();
        email = s.getEmail();
        coins = s.getCoins();

        StudentDto studentDto = new StudentDto( id, name, email, coins );

        return studentDto;
    }

    @Override
    public Student toEntity(StudentDto dto) {
        if ( dto == null ) {
            return null;
        }

        Student.StudentBuilder student = Student.builder();

        student.id( dto.id() );
        student.name( dto.name() );
        student.email( dto.email() );
        student.coins( dto.coins() );

        return student.build();
    }

    @Override
    public CourseDto toDto(Course c) {
        if ( c == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        String description = null;
        Long priceCoins = null;
        LocalDate startDate = null;

        id = c.getId();
        title = c.getTitle();
        description = c.getDescription();
        priceCoins = c.getPriceCoins();
        startDate = c.getStartDate();

        CourseDto courseDto = new CourseDto( id, title, description, priceCoins, startDate );

        return courseDto;
    }

    @Override
    public Course toEntity(CourseDto dto) {
        if ( dto == null ) {
            return null;
        }

        Course.CourseBuilder course = Course.builder();

        course.id( dto.id() );
        course.title( dto.title() );
        course.description( dto.description() );
        course.priceCoins( dto.priceCoins() );
        course.startDate( dto.startDate() );

        return course.build();
    }
}
