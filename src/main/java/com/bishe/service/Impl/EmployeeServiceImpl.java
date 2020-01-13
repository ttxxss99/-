package com.bishe.service.Impl;

import com.bishe.dao.EmployeeDao;
import com.bishe.model.Employee;
import com.bishe.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return employeeDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Employee employee) {
        employee.setLogicDel(1);
        return employeeDao.insert(employee);
    }

    @Override
    public Employee selectByPrimaryKey(Integer id) {
        return employeeDao.selectByPrimaryKey(id);
    }

    @Override
    public PageBean<Employee> selectAll(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<Employee> employees = employeeDao.selectAll();
        PageBean<Employee> pageData = new PageBean<>(currentPage, pageSize, employees.size());
        pageData.setItems(employees);
        return pageData;
    }

    @Override
    public int updateByPrimaryKey(Employee employee) {
        return employeeDao.updateByPrimaryKey(employee);
    }
}
