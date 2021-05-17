package com.example.company.service;

import com.example.company.dto.AddressDto;
import com.example.company.dto.ApiResponse;
import com.example.company.entity.Address;
import com.example.company.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public List<Address> allAddress(){
        return addressRepository.findAll();
    }

    public Address idAddress(Integer id){
        Optional<Address> optional=addressRepository.findById(id);
        return optional.orElse(new Address());
    }

    public ApiResponse addAddress(AddressDto addressDto){
        Address address=new Address(addressDto.getStreet(), addressDto.getHomenumber());
        addressRepository.save(address);
        return new ApiResponse("Address saqlandi",true);
    }
    public ApiResponse updateAddress(Integer id,AddressDto addressDto) {
        Optional<Address> optional = addressRepository.findById(id);
        if (optional.isPresent()) {
            Address address = optional.get();
            address.setHomenumber(addressDto.getHomenumber());
            address.setStreet(addressDto.getStreet());
            addressRepository.save(address);
            return new ApiResponse("Yangilandi", true);
        } else {
            return new ApiResponse("Bunday foydalanuvchi topilmadi !!!", false);
        }
    }

    public ApiResponse deleteAddress(Integer id){
        Optional<Address> optional=addressRepository.findById(id);
        if (optional.isPresent()){
            addressRepository.deleteById(id);
            return new ApiResponse("O'chirildi",true);
        }else {
            return new ApiResponse("Bunday foydalanuvchi yo'q",false);
        }
    }

}
