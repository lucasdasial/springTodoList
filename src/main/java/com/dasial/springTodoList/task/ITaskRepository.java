package com.dasial.springTodoList.task;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ITaskRepository
 */
public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {

}