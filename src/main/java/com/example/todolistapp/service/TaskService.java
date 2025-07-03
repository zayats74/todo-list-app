package com.example.todolistapp.service;

import com.example.todolistapp.dto.TaskRequestDTO;
import com.example.todolistapp.dto.TaskResponseDTO;
import com.example.todolistapp.dto.TaskUpdateDTO;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskResponseDTO getTaskById(UUID id);
    List<TaskResponseDTO> getAllTasks();
    UUID createTask(TaskRequestDTO taskRequestDTO);
    TaskResponseDTO updateTask(UUID id, TaskUpdateDTO taskUpdateDTO);
    void deleteTask(UUID id);
    TaskResponseDTO completeTask(UUID id);
}
