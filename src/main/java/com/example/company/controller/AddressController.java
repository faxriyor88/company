package com.example.company.controller;

import com.example.company.dto.AddressDto;
import com.example.company.dto.ApiResponse;
import com.example.company.entity.Address;
import com.example.company.service.AddressService;
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
@RequestMapping("/api/company")
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping("/address")
    public ResponseEntity<List<Address>> getAllAddress() {
        return ResponseEntity.ok(addressService.allAddress());
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Address> getIdAddress(@PathVariable Integer id) {
        return ResponseEntity.ok(addressService.idAddress(id));

    }

    @PostMapping("/address")
    public ResponseEntity<ApiResponse> insert(@Valid @RequestBody AddressDto addressDto) {
        ApiResponse apiResponse = addressService.addAddress(addressDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id,@Valid @RequestBody AddressDto addressDto){
        ApiResponse apiResponse = addressService.updateAddress(id, addressDto);
        if (apiResponse.isSuccess()){
            return ResponseEntity.status(201).body(apiResponse);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }
    @DeleteMapping("/address{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
        ApiResponse apiResponse= addressService.deleteAddress(id);
        if (apiResponse.isSuccess()){
            return ResponseEntity.status(201).body(apiResponse);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
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
