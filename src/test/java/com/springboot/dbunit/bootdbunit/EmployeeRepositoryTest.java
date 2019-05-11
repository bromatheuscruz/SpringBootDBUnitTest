package com.springboot.dbunit.bootdbunit;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.springboot.dbunit.bootdbunit.models.Employee;
import com.springboot.dbunit.bootdbunit.repositories.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@DatabaseSetup("/EmployeeRepositoryTest.xml")
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({
        TransactionalTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@Transactional
@TestPropertySource(locations = "/application-test.properties")
public class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void findByName_WhenExist_ShouldBeExist()
    {
        // given
        Employee expected = Employee.of(1, "John", "ADMIN");

        // when
        Optional<Employee> actual = employeeRepository.findByName("John");

        // then
        assertThat(actual, equalTo(Optional.of(expected)));
    }

    @Test
    @ExpectedDatabase("/EmployeeRepositoryTest_createNew_ShouldBeMore.xml")
    public void createNew_ShouldBeMore()
    {
        // given
        Employee employee = Employee.of(null, "Matheus", "ADMIN");

        // when
        employeeRepository.save(employee);
    }
}
