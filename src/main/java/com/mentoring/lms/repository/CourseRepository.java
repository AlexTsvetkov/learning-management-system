package com.mentoring.lms.repository;

import com.mentoring.lms.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByStartDate(LocalDate date);
}
