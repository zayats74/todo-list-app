package com.example.todolistapp.controller;

import com.example.todolistapp.dto.TaskRequestDTO;
import com.example.todolistapp.dto.TaskResponseDTO;
import com.example.todolistapp.dto.TaskUpdateDTO;
import com.example.todolistapp.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public UUID addTask(@RequestBody @Valid TaskRequestDTO task) {
        return taskService.createTask(task);
    }

    @GetMapping("/{id}")
    public TaskResponseDTO getTask(@PathVariable UUID id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public TaskResponseDTO updateTask(@PathVariable UUID id, @RequestBody @Valid TaskUpdateDTO task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
    }

    @GetMapping
    public List<TaskResponseDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PatchMapping("/{id}/complete")
    public TaskResponseDTO completeTask(@PathVariable UUID id) {
        return taskService.completeTask(id);
    }
}
