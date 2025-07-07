package com.example.todolistapp.service.Impl;

import com.example.todolistapp.dto.TaskRequestDTO;
import com.example.todolistapp.dto.TaskResponseDTO;
import com.example.todolistapp.entity.Task;
import com.example.todolistapp.enums.Status;
import com.example.todolistapp.mapper.TaskMapper;
import com.example.todolistapp.repository.TaskRepository;
import com.example.todolistapp.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    @Transactional
    public TaskResponseDTO getTaskById(UUID id) {
        Task task = findTaskById(id);
        log.info("Got task {}", task.getId());
        return taskMapper.mapToResponseDTO(task);
    }

    @Override
    @Transactional
    public List<TaskResponseDTO> getAllTasks() {
        log.info("Got all tasks");
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
                .available(true)
                .build();
        task = taskRepository.save(task);
        log.info("Task {} created", task.getId());
        return task.getId();
    }

    @Override
    @Transactional
    public TaskResponseDTO updateTask(UUID id, TaskRequestDTO taskRequestDTO) {
        Task task = findTaskById(id);
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        task = taskRepository.save(task);
        log.info("Task {} updated", task.getId());
        return taskMapper.mapToResponseDTO(task);
    }

    @Override
    @Transactional
    public void deleteTask(UUID id) {
        Task task = findTaskById(id);
        task.setAvailable(false);
        log.info("Task {} deleted", task.getId());
    }

    @Override
    @Transactional
    public TaskResponseDTO completeTask(UUID id) {
        taskRepository.updateStatus(id, Status.COMPLETED);
        log.info("Task {} completed", id);
        return taskMapper.mapToResponseDTO(findTaskById(id));
    }

    private Task findTaskById(UUID id) {
        try {
            return taskRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        }
        catch (EntityNotFoundException ex){
            log.error("Task {} not found", id);
            throw ex;
        }
    }
}
