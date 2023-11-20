package com.example.RentalHive.web.rest;

import com.example.RentalHive.Entities.Users;
import com.example.RentalHive.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserRest {
    @Qualifier("userService")
    private final UserService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Users>>> getAllUsers() {
        List<Users> users = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("users retrieved successful", users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Users>> getUserById(@PathVariable Long id) {
        Optional<Users> user = service.findById(id);
        return user.map(value -> ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(null, value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("user not found")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Users>> updateUser(@PathVariable Long id, @RequestBody Users user) {
        Optional<Users> existingUser = service.findById(id);
        return existingUser.map(u -> {
            user.setId(id);
            Users updatedUser = service.update(user);
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("user updated successful", updatedUser));
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("user not found")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Users>> deleteUser(@PathVariable Long id) {
        Optional<Users> deletedUser = service.delete(id);
        return deletedUser.map(value -> ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("user deleted successful", value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("user not found")));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Users>> register(@RequestBody Users user) {
        Users savedUser = service.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("user created successful", savedUser));
    }
}
