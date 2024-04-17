package com.PDP.repository;

import com.PDP.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee,Long> {
    Optional<Employee> findByEmail(String email);


}
