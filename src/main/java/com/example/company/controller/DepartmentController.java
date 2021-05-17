package com.example.company.controller;

import com.example.company.dto.ApiResponse;
import com.example.company.dto.DepartmentDto;
import com.example.company.entity.Department;
import com.example.company.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping("/department")
    public ResponseEntity<ApiResponse> insert(@Valid @RequestBody DepartmentDto departmentDto) {
        ApiResponse apiResponse = departmentService.addDepartment(departmentDto);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(201).body(apiResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @PutMapping("/department/{id}")
    public ResponseEntity<ApiResponse> insert(@PathVariable Integer id, @Valid @RequestBody DepartmentDto departmentDto) {
        ApiResponse apiResponse = departmentService.updateDepartment(id, departmentDto);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(201).body(apiResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = departmentService.deleteDepartment(id);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(200).body(apiResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @GetMapping("/department")
    public List<Department> getAll() {
        return departmentService.getAllDepartment();
    }

    @GetMapping("/department/{id}")
    public Department getOneDepartment(@PathVariable Integer id) {
        return departmentService.getIdDepartment(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
