package com.plinfotech.spring_boot_crud_employeeManagement.repository;

import com.plinfotech.spring_boot_crud_employeeManagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findByFirstName(String name);
}
