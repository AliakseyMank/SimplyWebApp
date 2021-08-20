package com.example.Restfull.service;

import com.example.Restfull.dao.EmployeeDao;
import com.example.Restfull.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    private final EmployeeDao employeeDao;


    public void create(Employee employee) {
        employeeDao.create(employee);
    }

    public List<Employee> readAll() {
        return employeeDao.readAll();
    }

    public Employee read(Long id) {
        return employeeDao.read(id);
    }

    public boolean update(Employee employee, Long id) {
        return employeeDao.update(employee, id);
    }

    public boolean delete(Long id) {
        return employeeDao.delete(id);
    }
}
