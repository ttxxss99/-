package com.bishe.service;

import com.bishe.model.Employee;
import com.utils.PageBean;

import java.util.List;

public interface EmployeeService {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee employee);

    PageBean<Employee> selectByPrimaryKey(Employee employee,int currentPage, int pageSize);

    PageBean<Employee> selectAll(int currentPage, int pageSize);

    int updateByPrimaryKey(Employee record);
}
