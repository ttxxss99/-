package com.bishe.controller;

import com.bishe.model.SalaryDetail;
import com.bishe.model.vo.SalaryDetailVo;
import com.bishe.service.SalaryDetailService;
import com.utils.LoggerUtil;
import com.utils.PageBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/salaryDetail")
public class SalaryDetailController {

    @Autowired
    SalaryDetailService salaryDetailService;

    @ApiOperation(value = "修改明细")
    @PostMapping(value = "/update", consumes = "application/json")
    public Object update(@RequestBody Map paramsMap) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        SalaryDetailVo salaryDetailVo = (SalaryDetailVo) com.utils.BeanUtils.mapToObject(paramsMap, SalaryDetailVo.class);
        SalaryDetail salaryDetail = new SalaryDetail();
        salaryDetailVo.setTime(new Date());
        BeanUtils.copyProperties(salaryDetailVo, salaryDetail);

        LoggerUtil.error(this.getClass().getName(), "update");

        if (null == salaryDetail) {
            map.put("data", "失败");
            return map;
        }
        int n = salaryDetailService.updateByPrimaryKey(salaryDetail);
        if (n > 0) {
            map.put("data", "成功");
            return map;
        } else {
            map.put("data", "失败");
            return map;
        }
    }

    @ApiOperation(value = "获取单个")
    @PostMapping("/selectByName")
    public Object selectByName(@RequestBody Map paramsMap,int currentPage, int pageSize) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        SalaryDetailVo salaryDetailVo = (SalaryDetailVo) com.utils.BeanUtils.mapToObject(paramsMap, SalaryDetailVo.class);
        PageBean<SalaryDetailVo> salaryDetailVos = salaryDetailService.selectByPrimaryKey(salaryDetailVo,currentPage, pageSize);
        map.put("data", salaryDetailVos);
        LoggerUtil.error(this.getClass().getName(), "selectByName");
        return map;
    }

    @ApiOperation(value = "获取所有", notes = "获取所有")
    @PostMapping("/selectAll")
    public Object selectAll(int currentPage, int pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();

        PageBean<SalaryDetailVo> pageData = salaryDetailService.selectAll(currentPage, pageSize);

        map.put("data", pageData);

        return map;
    }

    @ApiOperation(value = "增加单个")
    @PostMapping(value = "/insert", consumes = "application/json")
    public Object insert(@RequestBody Map paramsMap) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        SalaryDetail salaryDetail = (SalaryDetail) com.utils.BeanUtils.mapToObject(paramsMap, SalaryDetail.class);
        salaryDetail.setTime(new Date());
        if (salaryDetail.getDay() == 0) {
            map.put("data", "失败");
            return map;
        }
        int n = salaryDetailService.insert(salaryDetail);

        if (n > 0) {
            map.put("data", "成功");
            return map;
        } else {
            map.put("data", "失败");
            return map;
        }
    }
}
