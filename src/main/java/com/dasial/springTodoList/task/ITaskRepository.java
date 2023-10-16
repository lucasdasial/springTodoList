package com.dasial.springTodoList.task;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ITaskRepository
 */
public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
  List<TaskModel> findByUserId(UUID userId);
}