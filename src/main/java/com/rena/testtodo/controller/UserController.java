package com.rena.testtodo.controller;


import com.rena.testtodo.dto.request.CreateUserRequest;
import com.rena.testtodo.dto.request.UpdateUserRequest;
import com.rena.testtodo.dto.response.GenericResponse;
import com.rena.testtodo.dto.response.UserResponse;
import com.rena.testtodo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<GenericResponse<UserResponse>> createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(userService.createUser(createUserRequest));
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<Optional<GenericResponse<UserResponse>>> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById2(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GenericResponse<UserResponse>> updateUser(@PathVariable("id") Long id, @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<GenericResponse<UserResponse>> partiallyUpdateUser(@PathVariable("id") Long id, @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse<?>> deleteUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
