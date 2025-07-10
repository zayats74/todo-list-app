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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    @Transactional(readOnly = true)
    public TaskResponseDTO getTaskById(UUID id) {
        Task task = findTaskById(id);
        log.info("Got task {}", task.getId());
        return taskMapper.mapToResponseDTO(task);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponseDTO> getAllTasks() {
        log.info("Got all tasks");
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UUID createTask(TaskRequestDTO taskRequestDTO) {
        Task task = taskMapper.mapToEntity(taskRequestDTO);
        task = taskRepository.save(task);
        log.info("Task {} created", task.getId());
        return task.getId();
    }

    @Override
    public TaskResponseDTO updateTask(UUID id, TaskRequestDTO taskRequestDTO) {
        Task task = findTaskById(id);

        if (!task.getAvailable()) {
            throw new IllegalStateException("Cannot update a deleted task");
        }

        if (task.getStatus().isTerminal()) {
            throw new IllegalStateException("Cannot update task in terminal status: " + task.getStatus());
        }

        if (taskRequestDTO.getTitle() != null) {
            task.setTitle(taskRequestDTO.getTitle());
        }
        if (taskRequestDTO.getDescription() != null) {
            task.setDescription(taskRequestDTO.getDescription());
        }

        Status newStatus = taskRequestDTO.getStatus();
        if (newStatus != null) {
            if (newStatus.isTerminal() && !task.getStatus().canTransitionTo(newStatus)) {
                throw new IllegalStateException("Invalid status transition: "
                        + task.getStatus() + " to " + newStatus);
            }

            task.setStatus(newStatus);

            if (newStatus == Status.CANCELLED) {
                task.setAvailable(false);
            }
        }

        task = taskRepository.save(task);
        log.info("Task {} updated. New status: {}", task.getId(), task.getStatus());
        return taskMapper.mapToResponseDTO(task);
    }

    @Override
    public void deleteTask(UUID id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Task not found with id " + id);
        }

        Task task = findTaskById(id);

        if (!task.getAvailable()) {
            throw new IllegalStateException("Task is already deleted");
        }

        task.setStatus(Status.CANCELLED);
        task.setAvailable(false);
        log.info("Task {} deleted", task.getId());
    }

    @Override
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
