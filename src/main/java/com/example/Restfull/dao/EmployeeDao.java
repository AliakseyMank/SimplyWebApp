package com.example.Restfull.dao;

import com.example.Restfull.entity.Employee;
import com.example.Restfull.entity.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeDao {

    private static final String SQL_SAVE_EMPLOYEE = "INSERT INTO employee(first_name, last_name, department_id, job_title, gender, date_of_birth) VALUES (?,?,?,?,?,?)";
    private static final String SQL_FIND_ONE_EMPLOYEE = "SELECT * FROM employee WHERE employee_id = ?";
    private static final String SQL_FIND_ALL_EMPLOYEES = "SELECT * FROM employee";
    private static final String SQL_DELETE_EMPLOYEE = "DELETE FROM employee WHERE employee_id = ?";
    private static final String SQL_UPDATE_EMPLOYEE = "UPDATE employee SET first_name = ?, last_name = ?, department_id = ?, job_title = ?, gender = ?, date_of_birth = ? WHERE employee_id = ?";

    @Autowired
    public EmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final JdbcTemplate jdbcTemplate;

    public void create(Employee employee) {
        jdbcTemplate.update(SQL_SAVE_EMPLOYEE,
                            employee.getFirstName(),
                            employee.getLastName(),
                            employee.getDepartmentId(),
                            employee.getJobTitle(),
                            employee.getGender().toString(),
                            employee.getDateOfBirth());
    }

    public List<Employee> readAll() {
        return jdbcTemplate.query(SQL_FIND_ALL_EMPLOYEES, this::mapRowToEmployee);
    }

    public Employee read(Long id) {
        return jdbcTemplate.queryForObject(SQL_FIND_ONE_EMPLOYEE, this::mapRowToEmployee, id);
    }


    public boolean update(Employee employee, Long id) {
        int i = jdbcTemplate.update(SQL_UPDATE_EMPLOYEE
                , employee.getFirstName()
                , employee.getLastName()
                , employee.getDepartmentId()
                , employee.getJobTitle()
                , employee.getGender().toString()
                , employee.getDateOfBirth()
                , id);
        System.out.println(employee + " "+ i);
        return true;
    }

    public boolean delete(Long id) {
        return jdbcTemplate.update(SQL_DELETE_EMPLOYEE, id) > 0;
    }

    private Employee mapRowToEmployee(ResultSet resultSet, int rowNum) throws SQLException {
            return Employee.builder()
                    .employeeId(resultSet.getLong("employee_id"))
                    .firstName(resultSet.getString("first_name"))
                    .lastName(resultSet.getString("last_name"))
                    .departmentId(resultSet.getLong("department_id"))
                    .jobTitle(resultSet.getString("job_title"))
                    .gender(Gender.valueOf(resultSet.getString("gender")))
                    .dateOfBirth(resultSet.getDate("date_of_birth"))
                    .build();


    }
}