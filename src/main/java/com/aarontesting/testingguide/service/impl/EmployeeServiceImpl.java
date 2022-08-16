package com.aarontesting.testingguide.service.impl;

import com.aarontesting.testingguide.model.Employee;
import com.aarontesting.testingguide.repository.EmployeeRepository;
import com.aarontesting.testingguide.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {

        Optional<Employee> savedEmployee = employeeRepository.findByEmail(employee.getEmail());
        if (savedEmployee.isPresent()) {
            throw new RuntimeException("Employee already exists with given email: " + employee.getEmail());
        }

        return employeeRepository.save(employee);
    }
}