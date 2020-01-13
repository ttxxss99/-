package com.bishe.service;

import com.bishe.model.Employee;
import com.utils.PageBean;

import java.util.List;

public interface EmployeeService {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee employee);

    Employee selectByPrimaryKey(Integer id);

    PageBean<Employee> selectAll(int currentPage, int pageSize);

    int updateByPrimaryKey(Employee record);
}
