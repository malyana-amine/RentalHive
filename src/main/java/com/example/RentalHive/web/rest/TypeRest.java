package com.example.RentalHive.web.rest;

import com.example.RentalHive.Entities.Type;
import com.example.RentalHive.service.TypeService;
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
        return new ResponseEntity<>(ApiResponse.success( null, types), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Type>> getTypeById(@PathVariable Long id) {
        Optional<Type> type = service.findById(id);
        return type.map(value -> new ResponseEntity<>(ApiResponse.success(null, value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(ApiResponse.error("type not found"), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Type>> createType(@RequestBody Type type) {
        Type savedType = service.save(type);
        return new ResponseEntity<>(ApiResponse.success("type created successful", savedType), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Type>> updateType(@PathVariable Long id, @RequestBody Type type) {
        Optional<Type> existingType = service.findById(id);
        if (existingType.isPresent()) {
            type.setId(id);
            Type updatedType = service.update(type);
            return new ResponseEntity<>(ApiResponse.success("type updated successful", updatedType), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ApiResponse.error("type not found"), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Type>> deleteType(@PathVariable Long id) {
        Optional<Type> deletedType = service.delete(id);
        return deletedType.map(value -> new ResponseEntity<>(ApiResponse.success("type deleted successful", value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(ApiResponse.error("type not found"), HttpStatus.NOT_FOUND));
    }
}
