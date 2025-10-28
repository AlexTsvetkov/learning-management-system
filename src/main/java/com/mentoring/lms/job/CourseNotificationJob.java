package com.mentoring.lms.job;

import com.mentoring.lms.entity.Course;
import com.mentoring.lms.entity.Enrollment;
import com.mentoring.lms.repository.CourseRepository;
import com.mentoring.lms.repository.EnrollmentRepository;
import com.mentoring.lms.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
public class CourseNotificationJob {
    private static final Logger log = LoggerFactory.getLogger(CourseNotificationJob.class);

    private final CourseRepository courseRepo;
    private final EnrollmentRepository enrollmentRepo;
    private final EmailService emailService;
    private final Executor emailExecutor;

    // cron: every day at 08:00
    @Scheduled(cron = "0 0 8 * * *")
    public void sendNotificationsForTomorrowCourses() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        List<Course> courses = courseRepo.findByStartDate(tomorrow);
        for (Course course : courses) {
            List<Enrollment> enrollments = enrollmentRepo.findByCourseWithStudent(course);
            for (Enrollment e : enrollments) {
                Runnable task = () -> {
                    try {
                        String subj = "Course starting tomorrow: " + course.getTitle();
                        String body = "Hi " + e.getStudent().getName() + ",\n\nYour course '"
                                + course.getTitle() + "' starts on " + course.getStartDate() + ".\n\nRegards\nLMS Team";
                        emailService.sendCourseStartingNotification(e.getStudent().getEmail(), subj, body);
                    } catch (Exception ex) {
                        log.error("Failed to send email", ex);
                    }
                };
                if (emailExecutor instanceof ExecutorService) {
                    ((ExecutorService) emailExecutor).submit(task);
                } else {
                    emailExecutor.execute(task);
                }
            }
        }
    }
}
