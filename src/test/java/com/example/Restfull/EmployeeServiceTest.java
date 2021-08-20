package com.example.Restfull;

import com.example.Restfull.entity.Employee;
import com.example.Restfull.entity.Gender;
import com.example.Restfull.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.Instant;
import java.util.Date;

import static java.util.Date.parse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.*;


@SpringBootTest
public class EmployeeServiceTest {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeServiceTest(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Test
    public void whenReadAllEmployeesFromDb () {
        assertThat(employeeService.readAll().isEmpty(), is(false));
    }

    @Test
    public void whenDeleteEmployeeFromDb () {
        int n = employeeService.readAll().size();
        employeeService.delete(1L);
        assertThat(employeeService.readAll().size(), is (n-1));
    }

    @Test
    public void whenCreateNewEmployee () {
        int n = employeeService.readAll().size();
        Date date = new Date(1999-10-10);
        employeeService.create(Employee.builder()
                .firstName("test")
                .lastName("test")
                .employeeId(3L)
                .jobTitle("test")
                .gender(Gender.MALE)
                .dateOfBirth(date)
                .build());
        assertThat(employeeService.readAll().size(), is (n+1));
    }



}
