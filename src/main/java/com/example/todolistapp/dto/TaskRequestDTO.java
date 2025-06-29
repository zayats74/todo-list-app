package com.example.todolistapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDTO {
    private UUID id;

    private String title;

    private String description;

    private LocalDate dueDate;

    private boolean isCompleted;
}
