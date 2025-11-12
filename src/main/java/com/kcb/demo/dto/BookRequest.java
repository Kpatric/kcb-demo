package com.kcb.demo.dto;


import jakarta.validation.constraints.*;

public record BookRequest(
        @NotBlank String title,
        @NotBlank String author,
        @Min(1400) @Max(2100) Integer year
) {}

