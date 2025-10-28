package com.mentoring.lms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentDto(Long id,
                         @NotBlank String name,
                         @Email @NotBlank String email,
                         @NotNull Long coins) {
}
