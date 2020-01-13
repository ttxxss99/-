package com.bishe.service.Impl;

import com.bishe.dao.PayStructureDao;
import com.bishe.model.Employee;
import com.bishe.model.PayStructure;
import com.bishe.service.PayStructureService;
import com.github.pagehelper.PageHelper;
import com.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayStructureServiceImpl implements PayStructureService {

    @Autowired
    PayStructureDao payStructureDao;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return payStructureDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PayStructure payStructure) {
        return payStructureDao.insert(payStructure);
    }

    @Override
    public PayStructure selectByPrimaryKey(Integer id) {
        return payStructureDao.selectByPrimaryKey(id);
    }

    @Override
    public PageBean<PayStructure> selectAll(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<PayStructure> payStructures = payStructureDao.selectAll();
        PageBean<PayStructure> pageData = new PageBean<>(currentPage, pageSize, payStructures.size());
        pageData.setItems(payStructures);
        return pageData;
    }

    @Override
    public int updateByPrimaryKey(PayStructure payStructure) {
        return payStructureDao.updateByPrimaryKey(payStructure);
    }
}
