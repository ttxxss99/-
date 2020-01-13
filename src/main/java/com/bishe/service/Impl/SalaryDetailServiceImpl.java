package com.bishe.service.Impl;

import com.bishe.dao.SalaryDetailDao;
import com.bishe.model.Employee;
import com.bishe.model.SalaryDetail;
import com.bishe.model.vo.SalaryDetailVo;
import com.bishe.service.SalaryDetailService;
import com.github.pagehelper.PageHelper;
import com.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryDetailServiceImpl implements SalaryDetailService {

    @Autowired
    SalaryDetailDao salaryDetailDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return salaryDetailDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SalaryDetail salaryDetail) {
        return salaryDetailDao.insert(salaryDetail);
    }

    @Override
    public List<SalaryDetailVo> selectByPrimaryKey(SalaryDetailVo salaryDetailVo) {
        return salaryDetailDao.selectByPrimaryKey(salaryDetailVo);
    }

    @Override
    public PageBean<SalaryDetailVo> selectAll(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<SalaryDetailVo> salaryDetailVos = salaryDetailDao.selectAll();
        PageBean<SalaryDetailVo> pageData = new PageBean<>(currentPage, pageSize, salaryDetailVos.size());
        pageData.setItems(salaryDetailVos);
        return pageData;
    }

    @Override
    public int updateByPrimaryKey(SalaryDetail salaryDetail) {
        return salaryDetailDao.updateByPrimaryKey(salaryDetail);
    }
}
