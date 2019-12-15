package com.salary.service.Impl;

import com.salary.model.Vo.PlaceListVo;
import com.salary.dao.PlaceDao;
import com.salary.model.Place;
import com.salary.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    PlaceDao placeDao;

    @Override
    public int insert(Place place) {
        return placeDao.insert(place);
    }

    @Override
    public int update(Place place) {
        return placeDao.updateByPrimaryKey(place);
    }

    @Override
    public List<PlaceListVo> selectAll() {
        return placeDao.selectAll();
    }
}
