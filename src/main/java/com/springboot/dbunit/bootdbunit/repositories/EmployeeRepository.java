package com.springboot.dbunit.bootdbunit.repositories;

import com.springboot.dbunit.bootdbunit.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
 * author bromatheuscruz 11/05/19 - 18:33
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByName(String name);
}
