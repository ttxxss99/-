package com.salary.service.Impl;

import com.salary.dao.EmployeeDao;
import com.salary.model.Employee;
import com.salary.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeDao employeeDao;

    @Override
    public int insert(Employee employee) {
        return employeeDao.insert(employee);
    }

    @Override
    public int update(Employee employee) {
        return employeeDao.updateByPrimaryKey(employee);
    }

    @Override
    public List<Employee> selectAll() {
        return employeeDao.selectAll();
    }

    @Override
    public Employee selectOne(int id) {
        return employeeDao.selectByPrimaryKey(id);
    }

    @Override
    public int delete(int i) {
        return employeeDao.deleteByPrimaryKey(i);
    }
}
