package com.example.todolistapp.dto;

import com.example.todolistapp.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO для создания/обновления задачи")
public class TaskRequestDTO {
    @Size(max = 255)
    @Schema(description = "Название задачи", example = "Task")
    private String title;

    @Size(max = 1024)
    @Schema(description = "Описание задачи", example = "Description of task")
    private String description;

    @Future
    private OffsetDateTime dueDateTime;

    @Schema(description = "Статус задачи", example = "IN_PROGRESS")
    @Enumerated(EnumType.STRING)
    private Status status;
}
