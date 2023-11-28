package com.example.RentalHive.web.rest;

import com.example.RentalHive.DTO.UsersDTO;
import com.example.RentalHive.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<ApiResponse<List<UsersDTO>>> getAllUsers() {
        List<UsersDTO> users = service.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("users retrieved successful", users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsersDTO>> getUserById(@PathVariable Long id) {
        Optional<UsersDTO> user = service.findById(id);

        return user
                .map(value -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(ApiResponse.success(null, value)))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("user not found")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UsersDTO>> updateUser(@PathVariable Long id, @RequestBody @Valid UsersDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.validationError(bindingResult));
        }

        Optional<UsersDTO> existingUser = service.findById(id);

        if (existingUser.isPresent()) {
            userDTO.setId(id);
            try {
                UsersDTO updatedUser = service.update(userDTO);
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(ApiResponse.success("user updated successful", updatedUser));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
            }
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("user not found"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<UsersDTO>> deleteUser(@PathVariable Long id) {
        Optional<UsersDTO> deletedUser = service.delete(id);

        return deletedUser
                .map(value -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(ApiResponse.success("user deleted successful", value)))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("user not found")));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UsersDTO>> register(@RequestBody @Valid UsersDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.validationError(bindingResult));
        }

        try {
            UsersDTO savedUser = service.save(userDTO);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(ApiResponse.success("user created successful", savedUser));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
}
