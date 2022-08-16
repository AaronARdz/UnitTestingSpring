package com.aarontesting.testingguide.repository;

import com.aarontesting.testingguide.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    // Define custom query using JPQL with index params
    @Query("SELECT e FROM Employee e where e.firstName = ?1 and e.lastName = ?2")
    Employee findByJPQL(String firstName, String lastName);

    // Define custom query using JPQL with named params
    @Query("SELECT e FROM Employee e where e.firstName = :firstName and e.lastName = :lastName")
    Employee findByJPQLNamedParams(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName
            );

}
