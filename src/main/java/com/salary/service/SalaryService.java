package com.salary.service;


import com.salary.model.Salary;

import java.util.List;

public interface SalaryService {
    int insert(Salary salary);
    int update(Salary salary);
    List<Salary> selectAll();
}
