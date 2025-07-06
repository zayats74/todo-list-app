package com.example.todolistapp.controller;

import com.example.todolistapp.dto.TaskRequestDTO;
import com.example.todolistapp.dto.TaskResponseDTO;
import com.example.todolistapp.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Tag(name = "Контроллер задач", description = "Позволяет оперировать задачами с помощью операций CRUD")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    @Operation(summary = "Добавление задачи", description = "Позволяет добавить задачу")
    public UUID addTask(@RequestBody @Valid TaskRequestDTO task) {
        return taskService.createTask(task);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение задачи", description = "Получение задачи по ID")
    public TaskResponseDTO getTask(@PathVariable @Parameter(name = "id", description = "ID задачи") UUID id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление задачи", description = "Обновление задачи по ID")
    public TaskResponseDTO updateTask(@PathVariable @Parameter(name = "id", description = "ID задачи") UUID id,
                                      @RequestBody @Valid TaskRequestDTO task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление задачи", description = "Удаление задачи по ID")
    public void deleteTask(@PathVariable @Parameter(name = "id", description = "ID задачи") UUID id) {
        taskService.deleteTask(id);
    }

    @GetMapping
    @Operation(summary = "Получение списка задач", description = "Получение всех имеющихся задач")
    public List<TaskResponseDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PatchMapping("/{id}/complete")
    @Operation(summary = "Заверешение задачи", description = "Перевод задачи в статус 'Завершено' по ID")
    public TaskResponseDTO completeTask(@PathVariable @Parameter(name = "id", description = "ID задачи") UUID id) {
        return taskService.completeTask(id);
    }
}
