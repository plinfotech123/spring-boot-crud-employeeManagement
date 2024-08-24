package com.plinfotech.spring_boot_crud_employeeManagement.serviceImpl;

import com.plinfotech.spring_boot_crud_employeeManagement.entity.Employee;
import com.plinfotech.spring_boot_crud_employeeManagement.exception.EmployeeNotFoudException;
import com.plinfotech.spring_boot_crud_employeeManagement.exception.UniqueConstraintViolationException;
import com.plinfotech.spring_boot_crud_employeeManagement.repository.EmployeeRepository;
import com.plinfotech.spring_boot_crud_employeeManagement.service.EmployeeService;
import com.plinfotech.spring_boot_crud_employeeManagement.util.ConstraintMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Employee saveEmployee(Employee employee) {
       try {
           employeeRepository.save(employee);
       }catch(DataIntegrityViolationException exception){
          String getConstraint=ConstraintMapping.extractConstraintNameFromException(exception);
          String field=ConstraintMapping.getFieldNameForConstraint(getConstraint);
           throw new UniqueConstraintViolationException("A record with the same " +field+ " value already exists.");
       }
       catch(Exception exception){
           log.info(exception.getMessage());
       }
        return employee;
    }

    @Override
    public List<Employee> saveEmployees(List<Employee> employees) {
        try{
            employeeRepository.saveAll(employees);
        }catch(DataIntegrityViolationException exception){
            String getConstraint=ConstraintMapping.extractConstraintNameFromException(exception);
            String field=ConstraintMapping.getFieldNameForConstraint(getConstraint);
            throw new UniqueConstraintViolationException("A record with the same " +field+ " value already exists.");
        }
        catch(Exception exception){
            log.info(exception.getMessage());
        }
        return employees;
    }

    @Override
    public List<Employee> getAllEmployees() throws EmployeeNotFoudException {
        List<Employee> employees= employeeRepository.findAll();
        if(employees.isEmpty()){
            throw new EmployeeNotFoudException("No Employees presented in table");
        }else {
            return employees;
        }
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) throws EmployeeNotFoudException {
       Optional<Employee>  employee=employeeRepository.findById(id);
        if(employee.isPresent()) {
            return employee;
        }else{
            throw new EmployeeNotFoudException("Employee with id "+id +" is not found");
        }

    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        return employeeRepository.findByFirstName(name);
    }

    @Override
    public void deleteEmployee(Long id) throws EmployeeNotFoudException {
        if(employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
        }else{
            throw new EmployeeNotFoudException("Employee with id "+id+" is not found");
        }
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) throws EmployeeNotFoudException {
        Optional<Employee> employeeById=employeeRepository.findById(id);
        if(employeeById.isPresent()){
            employeeById.get().setSalary(employee.getSalary());
            employeeById.get().setJobTitle(employee.getJobTitle());
            employeeById.get().setDepartment(employee.getDepartment());
            employeeById.get().setEmail(employee.getEmail());
            employeeById.get().setEmploymentStatus(employee.getEmploymentStatus());
            employeeRepository.save(employeeById.get());
            return employeeById.get();
        }else{
            throw new EmployeeNotFoudException("Employee with id "+id +" is not found.");
        }

    }
}
