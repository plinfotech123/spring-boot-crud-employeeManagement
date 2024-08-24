package com.plinfotech.spring_boot_crud_employeeManagement.controller;

import com.plinfotech.spring_boot_crud_employeeManagement.entity.Employee;
import com.plinfotech.spring_boot_crud_employeeManagement.exception.EmployeeNotFoudException;
import com.plinfotech.spring_boot_crud_employeeManagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/saveEmployee")
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee){
       Employee em= employeeService.saveEmployee(employee);
       return  ResponseEntity.ok(em);
    }
    @PostMapping("/saveEmployees")
    public ResponseEntity<List<Employee>> saveEmployees(@Valid @RequestBody List<Employee> employees){
       List<Employee> em= employeeService.saveEmployees(employees);
        return  ResponseEntity.ok(em);
    }

    //get emp by Id
    @GetMapping("/getEmployee/{id}")
    public ResponseEntity <Optional<Employee>> getEmpById( @PathVariable("id") Long id) throws EmployeeNotFoudException {
        Optional<Employee> em= employeeService.getEmployeeById(id);
        return  ResponseEntity.ok(em);
    }

    //get emp by Id
    @GetMapping("/getEmployees")
    public ResponseEntity <List<Employee>> getEmployess( ) throws EmployeeNotFoudException {
        List<Employee> em= employeeService.getAllEmployees();
        return  ResponseEntity.ok(em);
    }
    @DeleteMapping("/deleteEmp/{id}")
    public String deleteEmployeeById( @PathVariable("id") Long id) throws EmployeeNotFoudException {
        employeeService.deleteEmployee(id);
        return "Employee with id " + id + " is deleted";
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> deleteEmployeeById( @PathVariable("id") Long id, Employee employee) throws EmployeeNotFoudException {
        Employee employee1=employeeService.updateEmployee(id,employee);
        return ResponseEntity.ok(employee1);
    }

    //
}
