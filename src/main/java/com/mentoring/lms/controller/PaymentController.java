package com.mentoring.lms.controller;

import lombok.RequiredArgsConstructor;
import com.mentoring.lms.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
