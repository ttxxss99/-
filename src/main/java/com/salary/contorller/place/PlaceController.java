package com.salary.contorller.place;

import com.salary.model.Vo.PlaceListVo;
import com.salary.service.Impl.PlaceServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/place")
@Api(value = "地点模块",description = "地点模块接口")
public class PlaceController {
    @Autowired
    PlaceServiceImpl placeServiceimpl;

    @ApiOperation(value = "获取地点列表",notes = "获取所有地点列表")
    @GetMapping("/selectAll")
    public Object selectAll(){


        List<PlaceListVo> placeListVos = placeServiceimpl.selectAll();
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("data",placeListVos);
        return map;
    }
}
