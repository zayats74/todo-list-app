package com.example.todolistapp.service.Impl;

import com.example.todolistapp.dto.TaskRequestDTO;
import com.example.todolistapp.dto.TaskResponseDTO;
import com.example.todolistapp.entity.Task;
import com.example.todolistapp.mapper.TaskMapper;
import com.example.todolistapp.repository.TaskRepository;
import com.example.todolistapp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskResponseDTO getTaskById(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return taskMapper.mapToResponseDTO(task);
    }

    @Override
    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
        Task task = Task.builder()
                .id(UUID.randomUUID())
                .title(taskRequestDTO.getTitle())
                .description(taskRequestDTO.getDescription())
                .dueDate(taskRequestDTO.getDueDate())
                .isCompleted(false)
                .build();
        return taskMapper.mapToResponseDTO(taskRepository.save(task));
    }

    @Override
    public TaskResponseDTO updateTask(UUID id, TaskRequestDTO taskRequestDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setDueDate(taskRequestDTO.getDueDate());

        return taskMapper.mapToResponseDTO(taskRepository.save(task));
    }

    @Override
    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TaskResponseDTO completeTask(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setCompleted(true);
        return taskMapper.mapToResponseDTO(taskRepository.save(task));
    }
}
