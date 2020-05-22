package com.bishe.service;

import com.bishe.model.SalarySheet;
import com.bishe.model.vo.SalarySheetVo;
import com.utils.PageBean;

import java.util.List;

public interface SalarySheetService {
    int deleteByPrimaryKey(Integer id);

    int insert(SalarySheet salarySheet);

    SalarySheet selectByPrimaryKey(Integer id);

    PageBean<SalarySheetVo> selectAll(SalarySheetVo salarySheetVo,int currentPage, int pageSize);

    int updateByPrimaryKey(SalarySheet salarySheet);
}
