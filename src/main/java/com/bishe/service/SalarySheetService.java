package com.bishe.service;

import com.bishe.model.SalarySheet;

import java.util.List;

public interface SalarySheetService {
    int deleteByPrimaryKey(Integer id);

    int insert(SalarySheet salarySheet);

    SalarySheet selectByPrimaryKey(Integer id);

    List<SalarySheet> selectAll();

    int updateByPrimaryKey(SalarySheet salarySheet);
}
