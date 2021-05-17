package com.example.company.service;

import com.example.company.dto.ApiResponse;
import com.example.company.dto.WorkerDto;
import com.example.company.entity.Address;
import com.example.company.entity.Department;
import com.example.company.entity.Worker;
import com.example.company.repository.AddressRepository;
import com.example.company.repository.DepartmentRepository;
import com.example.company.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public ApiResponse addWorker(WorkerDto workerDto){
        boolean exists = workerRepository.existsByPhoneNumber(workerDto.getPhonenumber());
        if (exists){
            return new ApiResponse("Bunday Worker mavjud",false);
        }else {
            Optional<Department> optional=departmentRepository.findById(workerDto.getDepartment_id());
            if (optional.isPresent()){
                Address address=new Address(workerDto.getStreet(), workerDto.getHomenumber());
                addressRepository.save(address);
            Worker worker=new Worker(workerDto.getName(), workerDto.getPhonenumber(),address, optional.get());
            workerRepository.save(worker);
            return new ApiResponse("Ma'lumot qo'shildi",true);
        }else {
            return new ApiResponse("Bunday Deparment topilmadi !!!",true);
            }
        }

    }
    public ApiResponse updateWorker(Integer id,WorkerDto workerDto) {
        Optional<Worker> optional = workerRepository.findById(id);
        if (optional.isPresent()) {
            boolean b = workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhonenumber(), id);
            if (b) {
                return new ApiResponse("Bunday worker mavjud", false);
            } else {
                Optional<Department> optional1 = departmentRepository.findById(workerDto.getDepartment_id());
                if (optional1.isPresent()) {
                    Worker worker = optional.get();
                    Address address = worker.getAddress();
                    address.setHomenumber(workerDto.getHomenumber());
                    address.setStreet(workerDto.getStreet());
                    addressRepository.save(address);
                    worker.setWorkerName(workerDto.getName());
                    worker.setAddress(address);
                    worker.setDepartment(optional1.get());
                    worker.setPhoneNumber(workerDto.getPhonenumber());
                    workerRepository.save(worker);
                    return new ApiResponse("Yangilandi", true);
                }else {
                    return new ApiResponse("Bunday Department topilmadi !!!",false);
                }
            }
        }else {
            return new ApiResponse("Bunday Worker topilmadi !!!",false);
        }
    }
    public ApiResponse deleteWorker(Integer id){
        Optional<Worker> optional = workerRepository.findById(id);
        if (optional.isPresent()){
            addressRepository.delete(optional.get().getAddress());
            workerRepository.deleteById(id);
            return new ApiResponse("O'chirildi",false);
        }else {
            return new ApiResponse("Bunday Worker topilmadi !!!",false);
        }
    }

    public List<Worker> allWorker(){
        return workerRepository.findAll();
    }
    public Worker idWorker(Integer id){
        Optional<Worker> optional=workerRepository.findById(id);
        return optional.orElse(new Worker());
    }
}
