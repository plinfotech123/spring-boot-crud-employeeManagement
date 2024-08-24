package com.plinfotech.spring_boot_crud_employeeManagement.service;

import com.plinfotech.spring_boot_crud_employeeManagement.entity.Employee;
import com.plinfotech.spring_boot_crud_employeeManagement.exception.EmployeeNotFoudException;


import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> saveEmployees(List<Employee> employees);

    List<Employee> getAllEmployees() throws EmployeeNotFoudException;

    Optional<Employee> getEmployeeById(Long id) throws EmployeeNotFoudException;

    List<Employee> getEmployeesByName(String name);

    void deleteEmployee(Long id) throws EmployeeNotFoudException;

    Employee updateEmployee(Long id, Employee employee) throws EmployeeNotFoudException;

}
