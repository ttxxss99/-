package com.bishe.service;

import com.bishe.model.SalarySheet;
import com.utils.PageBean;

import java.util.List;

public interface SalarySheetService {
    int deleteByPrimaryKey(Integer id);

    int insert(SalarySheet salarySheet);

    SalarySheet selectByPrimaryKey(Integer id);

    PageBean<SalarySheet> selectAll(int currentPage, int pageSize);

    int updateByPrimaryKey(SalarySheet salarySheet);
}
