package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.ChangePasswordRequest;
import com.example.ecommerce.dtos.RegisterUserRequest;
import com.example.ecommerce.dtos.UpdateUserRequest;
import com.example.ecommerce.dtos.UserDto;
import com.example.ecommerce.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }


  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
    return userService.getUserById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping()
  public List<UserDto> getAllUsers(
    @RequestParam(required = false, defaultValue = "") String sort) {

    return userService.getAllUsers(sort);
  }

  @PostMapping
  public ResponseEntity<UserDto> createUser(
    @RequestBody RegisterUserRequest request,
    UriComponentsBuilder uriBuilder) {

    UserDto userDto = userService.createUser(request);
    URI uri = uriBuilder.path("users/{id}").buildAndExpand(userDto.getId()).toUri();
    return ResponseEntity.created(uri).body(userDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserDto> updateUser(@PathVariable Long id,
                                            @RequestBody UpdateUserRequest request) {
    UserDto userDto = userService.updateUser(id, request);

    return ResponseEntity.ok(userDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
    UserDto userDto = userService.deleteUser(id);

    return ResponseEntity.ok(userDto);
  }

  @PostMapping("/{id}/change-password")
  public ResponseEntity<Void> changePassword(@PathVariable Long id,
                                             @RequestBody ChangePasswordRequest request
  ) {

    userService.changePassword(id, request);

    return ResponseEntity.noContent().build();
  }

}
