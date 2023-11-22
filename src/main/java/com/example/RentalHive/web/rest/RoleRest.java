package com.example.RentalHive.web.rest;

import com.example.RentalHive.entity.Role;
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
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success( "roles retrieved successful", roles));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Role>> getRoleById(@PathVariable Long id) {
        Optional<Role> role = service.findById(id);
        return role.map(value -> ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(null, value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("role not found")));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Role>> createRole(@RequestBody Role role) {
        Role savedRole = service.save(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("role created successful", savedRole));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Role>> updateRole(@PathVariable Long id, @RequestBody Role role) {
        Optional<Role> existingRole = service.findById(id);
        if (existingRole.isPresent()) {
            role.setId(id);
            Role updatedRole = service.update(role);
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("role updated successful", updatedRole));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("role not found"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Role>> deleteRole(@PathVariable Long id) {
        Optional<Role> deletedRole = service.delete(id);
        return deletedRole.map(value -> ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("role deleted successful", value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("role not found")));
    }
}
