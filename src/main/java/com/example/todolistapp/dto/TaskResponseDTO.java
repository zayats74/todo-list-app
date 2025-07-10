package com.example.todolistapp.dto;

import com.example.todolistapp.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO для получения запрашиваемой задачи")
public class TaskResponseDTO {
    @Schema(description = "ID задачи")
    private UUID id;

    @Schema(description = "Название задачи")
    private String title;

    @Schema(description = "Описание задачи")
    private String description;

    @Schema(description = "Дата и время создания задачи")
    private OffsetDateTime dueDateTime;

    @Schema(description = "Статус задачи")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Schema(description = "Флаг для логического удаления задачи")
    private Boolean available;
}
