package com.aarontesting.testingguide.repository;

import com.aarontesting.testingguide.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    // JUnit test for save employee operation
    @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

        // given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("andre")
                .lastName("pierre")
                .email("gignac@gmail.com")
                .build();

        // when - action or behaviour that we are goint to test
        Employee savedEmployee = employeeRepository.save(employee);

        // then - verify the output
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    // JUnit test for
    @Test
    public void givenEmployeesList_whenFindAll_thenSaveEmployeesList() {
        // given -  precondition or setup
        Employee employee = Employee.builder()
                .firstName("andre")
                .lastName("pierre")
                .email("gignac@gmail.com")
                .build();

        Employee employee1 = Employee.builder()
                .firstName("nahuel")
                .lastName("guzman")
                .email("nahuel@gmail.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        // when - action or the behaviour that we are going to test
        List<Employee> employeeList = employeeRepository.findAll();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);

    }

        // JUnit test for get employee by id operation
        @DisplayName("JUnit test for get employee by id operation")
        @Test
        public void givenEmployeeId_whenFindById_thenReturnEmployeeObject() {
            // given -  precondition or setup
            Employee employee = Employee.builder()
                    .firstName("andre")
                    .lastName("pierre")
                    .email("gignac@gmail.com")
                    .build();

            employeeRepository.save(employee);

            // when - action or the behaviour that we are going to test
            Employee employeeDb = employeeRepository.getById(employee.getId());

            // then - verify the output
            assertThat(employeeDb).isNotNull();

        }

        // JUnit test for get employee by email operation
        @DisplayName("JUnit test for get employee by email operation")
        @Test
        public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {
            // given -  precondition or setup
            Employee employee = Employee.builder()
                    .firstName("Andre")
                    .lastName("pierre")
                    .email("gignac@email.com")
                    .build();
            employeeRepository.save(employee);
            // when - action or the behaviour that we are going to test
            Optional<Employee> employeeDb = employeeRepository.findByEmail(employee.getEmail());

            // then - verify the output
            assertThat(employeeDb).isNotNull();
        }

        // JUnit test for
        @Test
        public void givenEmployeeObject_whenUpdatEmployee_thenVerifyEmployeeWasUpdated() {
            // given -  precondition or setup
            Employee employee = Employee.builder()
                    .firstName("Andre")
                    .lastName("pierre")
                    .email("gignac@email.com")
                    .build();
            employeeRepository.save(employee);

            // when - action or the behaviour that we are going to test
            Employee employeeDb = employeeRepository.getById(employee.getId());
            employeeDb.setEmail("apg10@gmail.com");
            employeeDb.setFirstName("Bomboro");
            Employee updatedEmployee = employeeRepository.save(employeeDb);

            // then - verify the output
            assertThat(updatedEmployee.getEmail()).isEqualTo("apg10@gmail.com");
            assertThat(updatedEmployee.getFirstName()).isEqualTo("Bomboro");
        }

        // JUnit test for delete method by id
        @DisplayName("JUnit test for delete method by id")
        @Test
        public void givenEmployeeObject_whenDeleteByIdAndFindById_thenReturnEmptyResult() {
            // given -  precondition or setup
            Employee employee = Employee.builder()
                    .firstName("Andre")
                    .lastName("pierre")
                    .email("gignac@email.com")
                    .build();
            employeeRepository.save(employee);

            // when - action or the behaviour that we are going to test
            employeeRepository.deleteById(employee.getId());
            Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

            // then - verify the output
            assertThat(employeeOptional).isEmpty();
        }

        // JUnit test for custom query using JPQL with index
        @DisplayName("JUnit test for custom query using JPQL with index")
        @Test
        public void givenEmployeeObject_whenFindByJPQL_thenReturnEmployeeObject() {
            // given -  precondition or setup
            Employee employee = Employee.builder()
                    .firstName("Andre")
                    .lastName("pierre")
                    .email("gignac@email.com")
                    .build();
            employeeRepository.save(employee);

            // when - action or the behaviour that we are going to test
            Employee employeeDb = employeeRepository.findByJPQL("Andre", "pierre");

            // then - verify the output
            assertThat(employeeDb).isNotNull();
            assertThat(employeeDb.getFirstName()).isEqualTo("Andre");

        }

    // JUnit test for custom query using JPQL with named parameters
    @DisplayName("JUnit test for custom query using JPQL with named parameters")
    @Test
    public void givenEmployeeObject_whenFindByJPQLNamedParams_thenReturnEmployeeObject() {
        // given -  precondition or setup
        Employee employee = Employee.builder()
                .firstName("Andre")
                .lastName("pierre")
                .email("gignac@email.com")
                .build();
        employeeRepository.save(employee);

        // when - action or the behaviour that we are going to test
        Employee employeeDb = employeeRepository.findByJPQLNamedParams("Andre", "pierre");

        // then - verify the output
        assertThat(employeeDb).isNotNull();
        assertThat(employeeDb.getFirstName()).isEqualTo("Andre");

    }
}





















