package com.bishe.service;

import com.bishe.model.Employee;
import com.bishe.model.PayStructure;
import com.utils.PageBean;

import java.util.List;

public interface PayStructureService {
    int deleteByPrimaryKey(Integer id);

    int insert(PayStructure payStructure);

    PayStructure selectByPrimaryKey(Integer id);

    PageBean<PayStructure> selectAll(int currentPage, int pageSize);

    int updateByPrimaryKey(PayStructure record);
}
