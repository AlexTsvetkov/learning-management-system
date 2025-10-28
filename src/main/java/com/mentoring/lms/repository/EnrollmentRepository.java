package com.mentoring.lms.repository;

import com.mentoring.lms.entity.Course;
import com.mentoring.lms.entity.Enrollment;
import com.mentoring.lms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByCourse(Course course);

    boolean existsByStudentAndCourse(Student student, Course course);

    @Query("select e from Enrollment e join fetch e.student where e.course = :course")
    List<Enrollment> findByCourseWithStudent(@Param("course") Course course);
}
