package com.bank.repo;

import com.bank.entity.Bank;
import com.bank.entity.ServiceCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceCounterRepo extends JpaRepository<ServiceCounter, Long> {

    ServiceCounter findByName(String name);
}
