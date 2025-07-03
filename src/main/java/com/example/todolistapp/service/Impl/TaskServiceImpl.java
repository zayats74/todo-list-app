package com.example.todolistapp.service.Impl;

import com.example.todolistapp.dto.TaskRequestDTO;
import com.example.todolistapp.dto.TaskResponseDTO;
import com.example.todolistapp.entity.Task;
import com.example.todolistapp.enums.Status;
import com.example.todolistapp.mapper.TaskMapper;
import com.example.todolistapp.repository.TaskRepository;
import com.example.todolistapp.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
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
        Task task = findTaskById(id);
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
    public UUID createTask(TaskRequestDTO taskRequestDTO) {
        Task task = Task.builder()
                .id(UUID.randomUUID())
                .title(taskRequestDTO.getTitle())
                .description(taskRequestDTO.getDescription())
                .dueDateTime(taskRequestDTO.getDueDateTime())
                .status(Status.PENDING)
                .build();
        return taskMapper.mapToResponseDTO(taskRepository.save(task)).getId();
    }

    @Override
    public TaskResponseDTO updateTask(UUID id, TaskRequestDTO taskRequestDTO) {
        Task task = findTaskById(id);
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task.setDueDateTime(taskRequestDTO.getDueDateTime());

        return taskMapper.mapToResponseDTO(taskRepository.save(task));
    }

    @Override
    public void deleteTask(UUID id) {
        Task task = findTaskById(id);
        task.setAvailable(false);
    }

    @Override
    public void completeTask(UUID id) {
        taskRepository.updateStatus(id, Status.COMPLETED);
    }

    private Task findTaskById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task " + id + " not found"));
    }
}
