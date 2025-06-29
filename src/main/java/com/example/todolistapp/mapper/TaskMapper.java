package com.example.todolistapp.mapper;

import com.example.todolistapp.dto.TaskResponseDTO;
import com.example.todolistapp.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskResponseDTO mapToResponseDTO(Task task);
}
