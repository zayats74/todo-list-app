package com.example.todolistapp.repository;

import com.example.todolistapp.entity.Task;
import com.example.todolistapp.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Modifying
    @Query("UPDATE Task t SET t.status = :status WHERE t.id = :id")
    void updateStatus(@Param("id") UUID id, @Param("starus") Status status);
}
