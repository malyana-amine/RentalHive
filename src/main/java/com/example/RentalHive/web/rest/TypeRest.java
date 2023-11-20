package com.example.RentalHive.web.rest;

import com.example.RentalHive.Entities.Type;
import com.example.RentalHive.Service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/types")
public class TypeRest {
    @Qualifier("typeService")
    private final TypeService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Type>>> getAllTypes() {
        List<Type> types = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success( "types retrieved successful", types));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Type>> getTypeById(@PathVariable Long id) {
        Optional<Type> type = service.findById(id);
        return type.map(value -> ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(null, value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("type not found")));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Type>> createType(@RequestBody Type type) {
        Type savedType = service.save(type);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("type created successful", savedType));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Type>> updateType(@PathVariable Long id, @RequestBody Type type) {
        Optional<Type> existingType = service.findById(id);
        if (existingType.isPresent()) {
            type.setId(id);
            Type updatedType = service.update(type);
            return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("type updated successful", updatedType));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("type not found"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Type>> deleteType(@PathVariable Long id) {
        Optional<Type> deletedType = service.delete(id);
        return deletedType.map(value -> ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("type deleted successful", value)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("type not found")));
    }
}
