package com.example.company.controller;

import com.example.company.dto.ApiResponse;
import com.example.company.dto.WorkerDto;
import com.example.company.entity.Worker;
import com.example.company.service.WorkerService;
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
public class WorkerController {
    @Autowired
    WorkerService workerService;

    @PostMapping("/worker")
    public ResponseEntity<ApiResponse> insert(@Valid @RequestBody WorkerDto workerDto) {
        ApiResponse apiResponse = workerService.addWorker(workerDto);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(201).body(apiResponse);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
        }
    }

    @PutMapping("/worker/{id}")
    public ResponseEntity<ApiResponse> updateWorker(@PathVariable Integer id, @Valid @RequestBody WorkerDto workerDto) {
        ApiResponse apiResponse = workerService.updateWorker(id, workerDto);
        if (apiResponse.isSuccess()) {
            return ResponseEntity.status(200).body(apiResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @GetMapping("/worker")
    public List<Worker> getallWorker() {
        return workerService.allWorker();
    }

    @GetMapping("/worker/{id}")
    public Worker getOneWorker(@PathVariable Integer id) {
        return workerService.idWorker(id);
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
