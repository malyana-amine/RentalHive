package com.example.RentalHive.web.rest;

import com.example.RentalHive.DTO.RoleDTO;
import com.example.RentalHive.service.RoleService;
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
@RequestMapping("/api/roles")
public class RoleRest {
    @Qualifier("roleService")
    private final RoleService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleDTO>>> getAllRoles() {
        List<RoleDTO> roles = service.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success("roles retrieved successful", roles));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleDTO>> getRoleById(@PathVariable Long id) {
        Optional<RoleDTO> role = service.findById(id);

        return role
                .map(value -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(ApiResponse.success(null, value)))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("role not found")));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RoleDTO>> createRole(@RequestBody @Valid RoleDTO roleDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.validationError(bindingResult));
        }

        RoleDTO savedRole = service.save(roleDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("role created successful", savedRole));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleDTO>> updateRole(@PathVariable Long id, @RequestBody @Valid RoleDTO roleDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.validationError(bindingResult));
        }

        Optional<RoleDTO> existingRole = service.findById(id);

        if (existingRole.isPresent()) {
            roleDTO.setId(id);
            RoleDTO updatedRole = service.update(roleDTO);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ApiResponse.success("role updated successful", updatedRole));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("role not found"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleDTO>> deleteRole(@PathVariable Long id) {
        Optional<RoleDTO> deletedRole = service.delete(id);

        return deletedRole
                .map(value -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(ApiResponse.success("role deleted successful", value)))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("role not found")));
    }
}
