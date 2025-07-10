package com.example.todolistapp.controller;

import com.example.todolistapp.dto.TaskRequestDTO;
import com.example.todolistapp.dto.TaskResponseDTO;
import com.example.todolistapp.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Контроллер задач", description = "Позволяет оперировать задачами с помощью операций CRUD")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    @Operation(summary = "Добавление задачи", description = "Позволяет добавить задачу")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID addTask(@RequestBody @Valid TaskRequestDTO task) {
        log.info("POST api/v1/tasks - Creating task");
        return taskService.createTask(task);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение задачи", description = "Получение задачи по ID")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponseDTO getTask(@PathVariable @Parameter(name = "id", description = "ID задачи") UUID id) {
        log.debug("GET /api/v1/tasks/{} - Getting task with ID {}", id, id);
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление задачи", description = "Обновление задачи по ID")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponseDTO updateTask(@PathVariable @Parameter(name = "id", description = "ID задачи") UUID id,
                                      @RequestBody @Valid TaskRequestDTO task) {
        log.info("PUT /api/v1/tasks/{} - Updating task with ID {}", id, id);
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление задачи", description = "Удаление задачи по ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable @Parameter(name = "id", description = "ID задачи") UUID id) {
        log.warn("DELETE /api/v1/tasks/{} - Deleting task with ID {}", id, id);
        taskService.deleteTask(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка задач", description = "Получение всех имеющихся задач")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskResponseDTO> getAllTasks() {
        log.debug("GET /api/v1/tasks - Getting all tasks");
        return taskService.getAllTasks();
    }

    @PatchMapping("/{id}/complete")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Заверешение задачи", description = "Перевод задачи в статус 'Завершено' по ID")
    public TaskResponseDTO completeTask(@PathVariable @Parameter(name = "id", description = "ID задачи") UUID id) {
        log.info("PATCH /api/v1/tasks/{}/complete - Completing task with ID {}", id, id);
        return taskService.completeTask(id);
    }
}
