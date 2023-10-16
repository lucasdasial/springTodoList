package com.dasial.springTodoList.task;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired
  private ITaskRepository repo;

  @PostMapping("/create")
  public ResponseEntity create(@RequestBody TaskModel data, HttpServletRequest request) {
    var task = this.repo.save(data);

    System.out.println(request.getAttribute("userId"));

    data.setUserId((UUID) request.getAttribute("userId"));

    return ResponseEntity.status(HttpStatus.CREATED).body(task);
  }
}
