package com.bishe.service.Impl;

import com.bishe.dao.SalarySheetDao;
import com.bishe.model.SalarySheet;
import com.bishe.service.SalarySheetService;
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
    public List<SalarySheet> selectAll() {
        return salarySheetDao.selectAll();
    }

    @Override
    public int updateByPrimaryKey(SalarySheet salarySheet) {
        return salarySheetDao.updateByPrimaryKey(salarySheet);
    }
}
