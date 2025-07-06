package com.example.todolistapp.dto;

import com.example.todolistapp.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Size(max = 255)
    @Schema(description = "Название задачи")
    private String title;

    @Size(max = 1024)
    @Schema(description = "Описание задачи")
    private String description;

    @NotNull
    @Schema(description = "Дата и время создания задачи")
    private OffsetDateTime dueDateTime;

    @NotNull
    @Schema(description = "Статус задачи")
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @Schema(description = "Флаг для логического удаления задачи")
    private Boolean available;
}
