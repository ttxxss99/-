package com.bishe.service;

import com.bishe.model.PayStructure;
import com.bishe.model.vo.PayStructureVo;
import com.utils.PageBean;

public interface PayStructureService {
    int deleteByPrimaryKey(Integer id);

    int insert(PayStructure payStructure);

    PayStructure selectByPrimaryKey(Integer id);

    PageBean<PayStructureVo> selectAll(int currentPage, int pageSize);

    int updateByPrimaryKey(PayStructure record);
}
