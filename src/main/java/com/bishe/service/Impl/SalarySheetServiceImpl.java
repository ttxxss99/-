package com.bishe.service.Impl;

import com.bishe.dao.SalaryDetailDao;
import com.bishe.dao.SalarySheetDao;
import com.bishe.model.Employee;
import com.bishe.model.SalarySheet;
import com.bishe.model.vo.SalaryDetailVo;
import com.bishe.model.vo.SalarySheetVo;
import com.bishe.service.SalarySheetService;
import com.github.pagehelper.PageHelper;
import com.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalarySheetServiceImpl implements SalarySheetService {

    @Autowired
    SalarySheetDao salarySheetDao;

    @Autowired
    SalaryDetailDao salaryDetailDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        SalarySheetVo salarySheetVo = new SalarySheetVo();
        salarySheetVo.setId(id);
        List<SalarySheetVo> salarySheetVos = salarySheetDao.selectAll(salarySheetVo);
        SalaryDetailVo salaryDetailVo = new SalaryDetailVo();
        salaryDetailVo.seteId(salarySheetVos.get(0).geteId());
        List<SalaryDetailVo> salaryDetailVos = salaryDetailDao.selectByPrimaryKey(salaryDetailVo);
        List<Integer> ids = salaryDetailVos.stream().map(SalaryDetailVo::getId).collect(Collectors.toList());
        ids.forEach((id1)->{
            salaryDetailDao.deleteByPrimaryKey(id1);
        });
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
    public PageBean<SalarySheetVo> selectAll(SalarySheetVo salarySheetVo,int currentPage, int pageSize) {
        int i = salarySheetDao.selectAll(salarySheetVo).size();
        PageHelper.startPage(currentPage, pageSize);
        List<SalarySheetVo> salarySheets = salarySheetDao.selectAll(salarySheetVo);
        PageBean<SalarySheetVo> pageData = new PageBean<>(currentPage, pageSize, salarySheets.size());
        pageData.setItems(salarySheets);
        pageData.setTotalNum(i);
        return pageData;
    }

    @Override
    public int updateByPrimaryKey(SalarySheet salarySheet) {
        return salarySheetDao.updateByPrimaryKey(salarySheet);
    }
}
