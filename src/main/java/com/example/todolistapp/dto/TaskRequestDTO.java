package com.example.todolistapp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {
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
