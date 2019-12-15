package com.salary.service.Impl;

import com.salary.dao.SalaryDao;
import com.salary.model.Salary;
import com.salary.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    SalaryDao salaryDao;

    @Override
    public int insert(Salary salary) {
        return salaryDao.insert(salary);
    }

    @Override
    public int update(Salary salary) {
        return salaryDao.updateByPrimaryKey(salary);
    }

    @Override
    public List<Salary> selectAll() {
        return salaryDao.selectAll();
    }
}
