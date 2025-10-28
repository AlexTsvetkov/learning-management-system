package com.mentoring.lms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CourseDto(Long id,
                        @NotBlank String title,
                        String description,
                        @NotNull Long priceCoins,
                        @NotNull LocalDate startDate) {
}
