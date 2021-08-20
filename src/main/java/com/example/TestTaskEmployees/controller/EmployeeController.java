package com.example.TestTaskEmployees.controller;

import com.example.TestTaskEmployees.entity.Employee;
import com.example.TestTaskEmployees.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EmployeeController {

    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping(value = "/employees")
    public ResponseEntity<?> create(@RequestBody Employee employee){
        employeeService.create(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<List<Employee>> read(){
        final List<Employee> employees = employeeService.readAll();
        return employees != null && !employees.isEmpty()
                ? new ResponseEntity<>(employees, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/employees/{id}")
    public ResponseEntity<Employee> read(@PathVariable (name = "id") Long id){
        final Employee employee = employeeService.read(id);
        return employeeService.read(id) != null
                ? new ResponseEntity<>(employee, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/employees/{id}")
    public ResponseEntity<?> update (@PathVariable (name = "id") Long id, @RequestBody Employee employee){
        return employeeService.update(employee, id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/employees/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        return employeeService.delete(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


}
