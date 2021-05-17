package com.example.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkerDto {
    @NotNull(message ="name bo'sh bo'lmasligi kerak !!!" )
    private String name;
    @NotNull(message ="phonenumber bo'sh bo'lmasligi kerak !!!" )
    private String phonenumber;
    @NotNull(message ="homenumber bo'sh bo'lmasligi kerak !!!" )
    private String homenumber;
    @NotNull(message ="street bo'sh bo'lmasligi kerak !!!" )
    private String street;
    @NotNull(message ="department_id bo'sh bo'lmasligi kerak !!!" )
    private Integer department_id;

}
