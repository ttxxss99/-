package com.salary.service.Impl;

import com.salary.dao.PlaceAndEmployeeDao;
import com.salary.model.PlaceAndEmployee;
import com.salary.service.PlaceAndEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaceAndEmployeeServiceImpl implements PlaceAndEmployeeService {

    @Autowired
    PlaceAndEmployeeDao placeAndEmployeeDao;

    @Override
    public int insert(PlaceAndEmployee placeAndEmployee) {
        return placeAndEmployeeDao.insert(placeAndEmployee);
    }

    @Override
    public int delete(PlaceAndEmployee placeAndEmployee) {
        return placeAndEmployeeDao.updateByPrimaryKey(placeAndEmployee);
    }
}
