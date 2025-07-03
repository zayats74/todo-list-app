package com.example.todolistapp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponseDTO {
    private UUID id;

    @Max(value = 255)
    private String title;

    @Max(value = 1024)
    private String description;

    @NotNull
    private LocalDate dueDate;

    @NotNull
    private boolean isCompleted;
}
