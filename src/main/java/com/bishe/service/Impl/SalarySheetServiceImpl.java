package com.bishe.service.Impl;

import com.bishe.dao.SalarySheetDao;
import com.bishe.model.Employee;
import com.bishe.model.SalarySheet;
import com.bishe.model.vo.SalarySheetVo;
import com.bishe.service.SalarySheetService;
import com.github.pagehelper.PageHelper;
import com.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalarySheetServiceImpl implements SalarySheetService {

    @Autowired
    SalarySheetDao salarySheetDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return salarySheetDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SalarySheet salarySheet) {
        return salarySheetDao.insert(salarySheet);
    }

    @Override
    public SalarySheet selectByPrimaryKey(Integer id) {
        return salarySheetDao.selectByPrimaryKey(id);
    }

    @Override
    public PageBean<SalarySheetVo> selectAll(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<SalarySheetVo> salarySheets = salarySheetDao.selectAll();
        PageBean<SalarySheetVo> pageData = new PageBean<>(currentPage, pageSize, salarySheets.size());
        pageData.setItems(salarySheets);
        return pageData;
    }

    @Override
    public int updateByPrimaryKey(SalarySheet salarySheet) {
        return salarySheetDao.updateByPrimaryKey(salarySheet);
    }
}
