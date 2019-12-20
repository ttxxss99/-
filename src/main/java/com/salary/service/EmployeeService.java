package com.salary.service;

import com.salary.model.Employee;

import java.util.List;

public interface EmployeeService {
    int insert(Employee employee);
    int update(Employee employee);
    List<Employee> selectAll();
    Employee selectOne(int id);
    int delete(int i);
}
