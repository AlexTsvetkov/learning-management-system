package com.mentoring.lms.service;

import com.mentoring.lms.entity.Course;
import com.mentoring.lms.entity.Enrollment;
import com.mentoring.lms.entity.Student;
import com.mentoring.lms.exception.InsufficientBalanceException;
import com.mentoring.lms.repository.CourseRepository;
import com.mentoring.lms.repository.EnrollmentRepository;
import com.mentoring.lms.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;
    private final EnrollmentRepository enrollmentRepo;

    @Transactional
    public void purchaseCourse(Long studentId, Long courseId) {
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new EntityNotFoundException("student"));
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new EntityNotFoundException("course"));

        if (enrollmentRepo.existsByStudentAndCourse(student, course)) {
            throw new IllegalStateException("already enrolled");
        }

        if (student.getCoins() < course.getPriceCoins()) {
            throw new InsufficientBalanceException("Not enough coins");
        }

        student.setCoins(student.getCoins() - course.getPriceCoins());
        studentRepo.save(student);

        Enrollment e = Enrollment.builder().student(student).course(course).enrolledAt(Instant.now()).build();
        enrollmentRepo.save(e);
    }
}
