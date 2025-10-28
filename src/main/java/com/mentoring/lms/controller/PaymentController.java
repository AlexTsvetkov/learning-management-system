package com.mentoring.lms.controller;

import com.mentoring.lms.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/purchase")
    public ResponseEntity<Void> purchase(@RequestParam Long studentId, @RequestParam Long courseId) {
        paymentService.purchaseCourse(studentId, courseId);
        return ResponseEntity.ok().build();
    }
}
