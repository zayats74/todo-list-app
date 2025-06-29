package com.example.todolistapp.service;

import com.example.todolistapp.dto.TaskRequestDTO;
import com.example.todolistapp.dto.TaskResponseDTO;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskResponseDTO getTaskById(UUID id);
    List<TaskResponseDTO> getAllTasks();
    TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO);
    TaskResponseDTO updateTask(UUID id, TaskRequestDTO taskRequestDTO);
    void deleteTask(UUID id);
    TaskResponseDTO completeTask(UUID id);
}
