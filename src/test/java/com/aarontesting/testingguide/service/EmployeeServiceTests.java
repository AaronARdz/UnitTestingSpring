package com.aarontesting.testingguide.service;

import com.aarontesting.testingguide.model.Employee;
import com.aarontesting.testingguide.repository.EmployeeRepository;
import com.aarontesting.testingguide.service.impl.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.Optional;

public class EmployeeServiceTests {

    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    @BeforeEach
    public void setup() {
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    // JUnit test for
    @DisplayName("JUnit test for saveEmployee method")
    @Test
    public void givenEmployeeObject_WhenSaveEmployee_thenReturnEmployeeObject() {
        // given -  precondition or setup
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Andre")
                .lastName("Gignac")
                .email("apg@email.com")
                .build();
        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail())).willReturn(Optional.empty());
        BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);
        System.out.println(employeeRepository);
        System.out.println(employeeService);

        // when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeService.saveEmployee(employee);
        System.out.println(savedEmployee);

        // then - verify the output
        Assertions.assertThat(savedEmployee).isNotNull();

    }
}
