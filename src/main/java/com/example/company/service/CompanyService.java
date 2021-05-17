package com.example.company.service;

import com.example.company.dto.ApiResponse;
import com.example.company.dto.CompanyDto;
import com.example.company.entity.Address;
import com.example.company.entity.Company;
import com.example.company.repository.AddressRepository;
import com.example.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;

    public ApiResponse addCompany(CompanyDto companyDto) {
        Address address = new Address(companyDto.getAddressDto().getStreet(), companyDto.getAddressDto().getHomenumber());
        addressRepository.save(address);
        Company company = new Company(companyDto.getCorpname(), companyDto.getDirectorname(), address);
        companyRepository.save(company);
        return new ApiResponse("Ma'lumot qo'shildi", true);
    }

    public ApiResponse updateCompany(Integer id, CompanyDto companyDto) {
        Optional<Company> optional = companyRepository.findById(id);
        if (optional.isPresent()) {
            Company company = optional.get();
            Address address = company.getAddress();
            address.setStreet(companyDto.getAddressDto().getStreet());
            address.setHomenumber(companyDto.getAddressDto().getHomenumber());
            addressRepository.save(address);
            company.setCorpName(companyDto.getCorpname());
            company.setDirectorName(companyDto.getDirectorname());
            company.setAddress(address);
            companyRepository.save(company);
            return new ApiResponse("Yangilandi", true);
        } else {
            return new ApiResponse("Bunday foydalanuvchi yo'q", false);
        }
    }

    public ApiResponse deleteCompany(Integer id) {
        Optional<Company> optional = companyRepository.findById(id);
        if (optional.isPresent()) {
            companyRepository.deleteById(id);
            addressRepository.delete(optional.get().getAddress());
            return new ApiResponse("O'chirildi", true);
        } else {
            return new ApiResponse("Bunday foydalanuvchi yo'q", false);
        }
    }

    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    public Company getIdCompany(Integer id) {
        Optional<Company> optional = companyRepository.findById(id);
        return optional.orElse(new Company());
    }
}