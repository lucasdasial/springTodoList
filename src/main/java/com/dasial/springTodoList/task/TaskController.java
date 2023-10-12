package com.dasial.springTodoList.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired
  private ITaskRepository repo;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody TaskModel data) {
    var task = this.repo.save(data);

    return ResponseEntity.status(HttpStatus.CREATED).body(task);
  }
}
