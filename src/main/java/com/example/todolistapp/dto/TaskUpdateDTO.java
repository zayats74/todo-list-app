package com.example.todolistapp.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class TaskUpdateDTO {
    private UUID id;

    @Size(max = 255)
    private String title;

    @Size(max = 1024)
    private String description;

    private Boolean available;
}
