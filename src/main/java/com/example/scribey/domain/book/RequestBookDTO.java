package com.example.scribey.domain.book;

import jakarta.validation.constraints.NotBlank;

public record RequestBookDTO(
        String id,
        String title,
        String description,
        String categoryId
) {
}
