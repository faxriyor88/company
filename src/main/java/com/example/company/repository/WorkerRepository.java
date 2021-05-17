package com.example.company.repository;

import com.example.company.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<Worker,Integer> {
     boolean existsByPhoneNumber(String phoneNumber);

     boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);
}
