package com.bishe.service.Impl;

import com.bishe.dao.SalaryDetailDao;
import com.bishe.dao.SalarySheetDao;
import com.bishe.model.Employee;
import com.bishe.model.SalaryDetail;
import com.bishe.model.SalarySheet;
import com.bishe.model.vo.SalaryDetailVo;
import com.bishe.service.SalaryDetailService;
import com.github.pagehelper.PageHelper;
import com.utils.BeanUtils;
import com.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryDetailServiceImpl implements SalaryDetailService {

    @Autowired
    SalaryDetailDao salaryDetailDao;

    @Autowired
    SalarySheetDao salarySheetDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return salaryDetailDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SalaryDetail salaryDetail) {

        int i = salaryDetailDao.insert(salaryDetail);
        SalarySheet salarySheet = salarySheetDao.selectByPrimaryKey(salaryDetail.geteId());
        if(null==salarySheet.getId()){
//            salaryDetailDao.selectByPrimaryKey()
        }
        return i;
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
        int i = salaryDetailDao.updateByPrimaryKey(salaryDetail);
        SalarySheet salarySheet = salarySheetDao.selectByPrimaryKey(salaryDetail.geteId());


        return i;
    }
}
