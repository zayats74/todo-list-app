package com.example.todolistapp.mapper;

import com.example.todolistapp.dto.TaskRequestDTO;
import com.example.todolistapp.dto.TaskResponseDTO;
import com.example.todolistapp.entity.Task;
import com.example.todolistapp.enums.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskResponseDTO mapToResponseDTO(Task task);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", expression = "java(getDefaultStatus())")
    @Mapping(target = "available", constant = "true")
    Task mapToEntity(TaskRequestDTO taskRequestDTO);

    @Named("getDefaultStatus")
    default Status getDefaultStatus() {
        return Status.PENDING;
    }
}
