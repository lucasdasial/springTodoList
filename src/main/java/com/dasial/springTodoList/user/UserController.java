package com.dasial.springTodoList.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * userController
 */

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private IUserRepository userRepository;

  @PostMapping("/create")
  public ResponseEntity create(@RequestBody UserModel data) {
    var user = this.userRepository.findByUsername(data.getUsername());

    if (user != null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body({messagem: '"User exist!"'});
    }

    var userCreated = this.userRepository.save(data);

    return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
  }

}