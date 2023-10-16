package com.dasial.springTodoList.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

  @PostMapping("/")
  public ResponseEntity create(@RequestBody TaskModel data, HttpServletRequest request) {

    data.setUserId((UUID) request.getAttribute("userId"));
    var currentDate = LocalDateTime.now();

    if (currentDate.isAfter(data.getStartAt()) ||
        currentDate.isAfter(data.getEndAt()) ||
        data.getStartAt().isAfter(data.getEndAt())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Invalid date");
    }

    var task = this.repo.save(data);
    return ResponseEntity.status(HttpStatus.CREATED).body(task);
  }

  @GetMapping("/")
  public ResponseEntity index(HttpServletRequest request) {
    var userId = request.getAttribute("userId");

    var tasks = this.repo.findByUserId((UUID) userId);

    return ResponseEntity.ok().body(tasks);

  }
}
