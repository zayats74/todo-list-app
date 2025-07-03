package com.example.todolistapp.dto;

import com.example.todolistapp.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.OffsetDateTime;
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
    private OffsetDateTime dueDateTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    private boolean isAvailable;
}
