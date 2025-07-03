package com.example.todolistapp.service.Impl;

import com.example.todolistapp.dto.TaskRequestDTO;
import com.example.todolistapp.dto.TaskResponseDTO;
import com.example.todolistapp.dto.TaskUpdateDTO;
import com.example.todolistapp.entity.Task;
import com.example.todolistapp.enums.Status;
import com.example.todolistapp.mapper.TaskMapper;
import com.example.todolistapp.repository.TaskRepository;
import com.example.todolistapp.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    @Transactional
    public TaskResponseDTO getTaskById(UUID id) {
        Task task = findTaskById(id);
        return taskMapper.mapToResponseDTO(task);
    }

    @Override
    @Transactional
    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UUID createTask(TaskRequestDTO taskRequestDTO) {
        Task task = Task.builder()
                .title(taskRequestDTO.getTitle())
                .description(taskRequestDTO.getDescription())
                .dueDateTime(OffsetDateTime.now())
                .status(Status.PENDING)
                .available(taskRequestDTO.getAvailable() != null
                ? taskRequestDTO.getAvailable()
                : true)
                .build();
        return taskMapper.mapToResponseDTO(taskRepository.save(task)).getId();
    }

    @Override
    @Transactional
    public TaskResponseDTO updateTask(UUID id, TaskUpdateDTO taskRequestDTO) {
        Task task = findTaskById(id);
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());

        return taskMapper.mapToResponseDTO(taskRepository.save(task));
    }

    @Override
    @Transactional
    public void deleteTask(UUID id) {
        Task task = findTaskById(id);
        task.setAvailable(false);
    }

    @Override
    @Transactional
    public TaskResponseDTO completeTask(UUID id) {
        taskRepository.updateStatus(id, Status.COMPLETED);
        return taskMapper.mapToResponseDTO(findTaskById(id));
    }

    private Task findTaskById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task " + id + " not found"));
    }
}
