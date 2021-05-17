package com.example.company.controller;

import com.example.company.dto.ApiResponse;
import com.example.company.dto.CompanyDto;
import com.example.company.entity.Company;



import com.example.company.service.CompanyService;
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
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @PostMapping("/company")
    public ResponseEntity<ApiResponse> insert(@Valid  @RequestBody CompanyDto companyDto){
        ApiResponse apiResponse = companyService.addCompany(companyDto);
        if (apiResponse.isSuccess()){
            return ResponseEntity.status(201).body(apiResponse);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }
    @PutMapping("/company/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id,@Valid @RequestBody CompanyDto companyDto){
        ApiResponse apiResponse = companyService.updateCompany(id,companyDto);
        if (apiResponse.isSuccess()){
            return ResponseEntity.status(201).body(apiResponse);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }
    @GetMapping("/company")
    public ResponseEntity<List<Company>> getAllCompany(){
        return ResponseEntity.ok(companyService.getAllCompany());
    }
    @GetMapping("/company/{id}")
    public ResponseEntity<Company> getOneCompany(@PathVariable Integer id){
        return ResponseEntity.ok(companyService.getIdCompany(id));
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
