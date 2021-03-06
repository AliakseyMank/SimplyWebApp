package com.example.TestTaskEmployees;

import com.example.TestTaskEmployees.controller.EmployeeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {
    @Autowired
    public SmokeTest(EmployeeController controller) {
        this.controller = controller;
    }

    private final EmployeeController controller;

    @Test
     void contextLoads() {
        assertThat(controller).isNotNull();
    }


}
