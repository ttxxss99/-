package com.bishe.controller;

import com.alibaba.fastjson.JSONObject;
import com.bishe.model.PayStructure;
import com.bishe.service.PayStructureService;
import com.utils.PageBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payStructure")
public class PayStructureController {

    @Autowired
    PayStructureService payStructureService;

    @ApiOperation(value = "获取工资结构列表", notes = "获取列表")
    @GetMapping("/selectAll")
    public Object selectAll(int currentPage, int pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();

        PageBean<PayStructure> pageData = payStructureService.selectAll(currentPage, pageSize);

        map.put("data", pageData);

        return map;
    }

    @ApiOperation(value = "修改工资结构")
    @PostMapping("/update")
    public Object update(@RequestBody JSONObject jsonObject) {
        Map<String, Object> map = new HashMap<String, Object>();
        PayStructure payStructure = jsonObject.getObject("data", PayStructure.class);
        if (null == payStructure.getBasic()) {
            map.put("data", "失败");
            return map;
        }
        int n = payStructureService.updateByPrimaryKey(payStructure);
        if (n > 0) {
            map.put("data", "成功");
            return map;
        } else {
            map.put("data", "失败");
            return map;
        }
    }

    @ApiOperation(value = "删除工资结构")
    @PostMapping(value = "/delete", consumes = "application/json")
    public Object delete(@RequestBody JSONObject jsonObject) {
        List<Integer> integers = (List<Integer>) jsonObject.get("data");
        Integer[] ids = new Integer[integers.size()];
        integers.toArray(ids);
        int n = 0;
        for (int i : ids) {
            n = payStructureService.deleteByPrimaryKey(i);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (n > 0) {
            map.put("data", "成功");
            return map;
        } else {
            map.put("data", "失败");
            return map;
        }
    }

    @ApiOperation(value = "增加工资结构")
    @PostMapping(value = "/insert", consumes = "application/json")
    public Object insert(@RequestBody JSONObject s) {
        Map<String, Object> map = new HashMap<String, Object>();
        PayStructure payStructure = s.getObject("data", PayStructure.class);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        payStructure.setTime(new Date());
        if (null==payStructure.getBasic()) {
            map.put("data", "失败");
            return map;
        }
        int n = payStructureService.insert(payStructure);

        if (n > 0) {
            map.put("data", "成功");
            return map;
        } else {
            map.put("data", "失败");
            return map;
        }
    }
}
