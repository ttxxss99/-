package com.salary.service;

import com.salary.model.Vo.PlaceListVo;
import com.salary.model.Place;

import java.util.List;

public interface PlaceService {
    int insert(Place place);
    int update(Place place);
    List<PlaceListVo> selectAll();
    List<PlaceListVo> selectByName(PlaceListVo placeListVo);
}
