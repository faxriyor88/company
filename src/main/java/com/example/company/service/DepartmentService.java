package com.example.company.service;

import com.example.company.dto.ApiResponse;
import com.example.company.dto.DepartmentDto;
import com.example.company.entity.Company;
import com.example.company.entity.Department;
import com.example.company.repository.CompanyRepository;
import com.example.company.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;

    public ApiResponse addDepartment(DepartmentDto departmentDto){
        Optional<Company> optional=companyRepository.findById(departmentDto.getCompany_id());
        if (optional.isPresent()){
            Department department=new Department(departmentDto.getDepartname(), optional.get());
            departmentRepository.save(department);
            return new ApiResponse("Ma'lumot qo'shildi",true);
        }else {
            return new ApiResponse("Bunday Company topilmadi !!!",false);
        }
    }
    public ApiResponse updateDepartment(Integer id,DepartmentDto departmentDto) {
        Optional<Department> optional = departmentRepository.findById(id);
        if (optional.isPresent()) {
            Optional<Company> optional1 = companyRepository.findById(departmentDto.getCompany_id());
            if (optional1.isPresent()) {
                Department department =optional.get();
                department.setDepartName(departmentDto.getDepartname());
                department.setCompany(optional1.get());
                departmentRepository.save(department);
                return new ApiResponse("Ma'lumot qo'shildi", true);
            } else {
                return new ApiResponse("Bunday Company topilmadi !!!", false);
            }

        }else {
            return new ApiResponse("Bunday Department topilmadi !!!",false);
        }
    }
    public ApiResponse deleteDepartment(Integer id){
        Optional<Department> optional = departmentRepository.findById(id);
        if (optional.isPresent()) {
            departmentRepository.deleteById(id);
            return new ApiResponse("O'chirildi",true);
        }else {
            return new ApiResponse("Bunday Department topilmadi !!!",false);
        }
    }

    public List<Department> getAllDepartment(){
        return departmentRepository.findAll();
    }
    public Department getIdDepartment(Integer id) {
        Optional<Department> optional = departmentRepository.findById(id);
        return optional.orElse(new Department());
    }
}
