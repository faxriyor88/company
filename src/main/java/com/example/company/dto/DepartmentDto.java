package com.example.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDto {
    @NotNull(message = "departname bo'sh bo'lmasligi kerak !!!")
    private String departname;
    @NotNull(message = "company_id bo'sh bo'lmasligi kerak !!!")
    private Integer company_id;
}
