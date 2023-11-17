package com.example.RentalHive.web.rest;

import com.example.RentalHive.Entities.Role;
import com.example.RentalHive.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<List<Role>>> getAllRoles() {
        List<Role> roles = service.findAll();
        return new ResponseEntity<>(ApiResponse.success( null, roles), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Role>> getRoleById(@PathVariable Long id) {
        Optional<Role> role = service.findById(id);
        return role.map(value -> new ResponseEntity<>(ApiResponse.success(null, value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(ApiResponse.error("role not found"), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Role>> createRole(@RequestBody Role role) {
        Role savedRole = service.save(role);
        return new ResponseEntity<>(ApiResponse.success("role created successful", savedRole), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Role>> updateRole(@PathVariable Long id, @RequestBody Role role) {
        Optional<Role> existingRole = service.findById(id);
        if (existingRole.isPresent()) {
            role.setId(id);
            Role updatedRole = service.update(role);
            return new ResponseEntity<>(ApiResponse.success("role updated successful", updatedRole), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ApiResponse.error("role not found"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Role>> deleteRole(@PathVariable Long id) {
        Optional<Role> deletedRole = service.delete(id);
        return deletedRole.map(value -> new ResponseEntity<>(ApiResponse.success("role deleted successful", value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(ApiResponse.error("role not found"), HttpStatus.NOT_FOUND));
    }
}
