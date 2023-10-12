package com.dasial.springTodoList.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  private String username;

  private String description;

  @Column(length = 50)
  private String title;

  private String priority;

  @CreationTimestamp
  private LocalDateTime createdAt;

  private LocalDateTime startAt;
  private LocalDateTime endAt;

  private UUID idUSer;

}