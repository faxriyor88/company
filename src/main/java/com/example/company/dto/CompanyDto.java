package com.example.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompanyDto {
    @NotNull(message = "corpname bo'sh bo'lishi kerak emas !!!")
    private String corpname;
    @NotNull(message = "directorname bo'sh bo'lishi kerak emas !!!")
    private String directorname;
    @NotNull(message = "address bo'sh bo'masligi kerak !!!")
    private AddressDto addressDto;
}
