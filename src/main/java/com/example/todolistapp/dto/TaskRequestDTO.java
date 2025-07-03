package com.example.todolistapp.dto;

import com.example.todolistapp.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {
    @Size(max = 255)
    private String title;

    @Size(max = 1024)
    private String description;

    private OffsetDateTime dueDateTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Boolean available;
}
