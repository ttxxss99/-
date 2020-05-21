package com.bishe.service;

import com.bishe.model.SalaryDetail;
import com.bishe.model.vo.SalaryDetailVo;
import com.utils.PageBean;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface SalaryDetailService {

    int deleteByPrimaryKey(Integer id);

    int insert(SalaryDetail salaryDetail) throws InvocationTargetException, IllegalAccessException;

    PageBean<SalaryDetailVo> selectByPrimaryKey(SalaryDetailVo salaryDetailVo,int currentPage, int pageSize);

    PageBean<SalaryDetailVo> selectAll(int currentPage, int pageSize);

    int updateByPrimaryKey(SalaryDetail salaryDetail);


}
