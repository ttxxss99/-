package com.salary.contorller.place;

import com.alibaba.fastjson.JSON;
import com.salary.model.PlaceAndEmployee;
import com.salary.model.Vo.PlaceListVo;
import com.salary.service.PlaceAndEmployeeService;
import com.salary.service.PlaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/place")
@Api(value = "地点模块",description = "地点模块接口")
public class PlaceController {
    @Autowired
    PlaceService placeService;

    @Autowired
    PlaceAndEmployeeService placeAndEmployeeService;

    @ApiOperation(value = "获取地点列表",notes = "获取所有地点列表")
    @GetMapping("/selectAll")
    public Object selectAll(){

        List<PlaceListVo> placeListVos = placeService.selectAll();
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("data",placeListVos);
        return map;
    }

    @PostMapping("/selectByName")
    public Object selectByName(HttpServletRequest request){
        String data = request.getParameter("data");
        PlaceListVo placeListVo =(PlaceListVo)JSON.parse(data);
        Map<String,Object> map =new HashMap<String,Object>();
        List<PlaceListVo> placeListVos = placeService.selectByName(placeListVo);
        map.put("data",placeListVos);
        return map;
    }

    @PostMapping("/insert")
    public void insert(HttpServletRequest request){
        String data = request.getParameter("data");
        List<PlaceListVo> placeListVos = JSON.parseArray(data, PlaceListVo.class);
        PlaceAndEmployee placeAndEmployee = new PlaceAndEmployee();
        for (PlaceListVo placeListVo: placeListVos) {
            placeAndEmployee.seteId(placeListVo.geteId());
            placeAndEmployee.setpId(placeListVo.getpId());
            placeAndEmployee.setDay(placeListVo.getDay());
            placeAndEmployeeService.insert(placeAndEmployee);
        }
    }
}
