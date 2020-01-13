package com.bishe.controller;

import com.alibaba.fastjson.JSONObject;
import com.bishe.model.Employee;
import com.bishe.model.SalaryDetail;
import com.bishe.model.vo.SalaryDetailVo;
import com.bishe.service.SalaryDetailService;
import com.utils.LoggerUtil;
import com.utils.PageBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/salaryDetail")
public class SalaryDetailController {

    @Autowired
    SalaryDetailService salaryDetailService;

    @ApiOperation(value = "修改明细")
    @PostMapping(value = "/update", consumes = "application/json")
    public Object update(@RequestBody JSONObject s) {
        Map<String, Object> map = new HashMap<String, Object>();
        SalaryDetailVo salaryDetailVo = s.getObject("data", SalaryDetailVo.class);
        SalaryDetail salaryDetail = new SalaryDetail();

        BeanUtils.copyProperties(salaryDetailVo,salaryDetail);

        LoggerUtil.error(this.getClass().getName(),"update");

        if (null == salaryDetail.getName()) {
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
    public Object selectByName(@RequestBody JSONObject data) {
        Map<String, Object> map = new HashMap<String, Object>();
        SalaryDetailVo salaryDetailVo = data.getObject("data", SalaryDetailVo.class);
        List<SalaryDetailVo> salaryDetailVos = salaryDetailService.selectByPrimaryKey(salaryDetailVo);
        map.put("data", salaryDetailVos);
        LoggerUtil.error(this.getClass().getName(),"selectByName");
        return map;
    }

    @ApiOperation(value = "获取所有", notes = "获取所有")
    @GetMapping("/selectAll")
    public Object selectAll(int currentPage, int pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();

        PageBean<SalaryDetailVo> pageData = salaryDetailService.selectAll(currentPage,pageSize);

        map.put("data", pageData);

        return map;
    }
}
