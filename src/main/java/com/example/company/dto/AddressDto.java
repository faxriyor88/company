package com.example.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDto {
    @NotNull(message = "street bo'sh bo'lmasligi kerak")
    private String street;
    @NotNull(message = "homenumber bo'sh bo'masligi kerak")
    private String homenumber;
}
