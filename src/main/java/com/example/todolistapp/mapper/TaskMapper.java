package com.example.todolistapp.mapper;

import com.example.todolistapp.dto.TaskResponseDTO;
import com.example.todolistapp.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(source = "available", target = "available")
    TaskResponseDTO mapToResponseDTO(Task task);
}
