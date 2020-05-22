package com.bishe.controller;

import com.bishe.model.Employee;
import com.bishe.model.vo.SalarySheetVo;
import com.bishe.service.SalarySheetService;
import com.utils.BeanUtils;
import com.utils.PageBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/salarySheet")
public class SalarySheetController {

    @Autowired
    SalarySheetService salarySheetService;

//    @ApiOperation(value = "获取列表")
//    @GetMapping("/selectAll")
//    public Object selectAll(int currentPage, int pageSize) {
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        PageBean<SalarySheetVo> pageData = salarySheetService.selectAll(currentPage,pageSize);
//
//        map.put("data", pageData);
//
//        return map;
//    }

    @ApiOperation(value = "获取单个员工", notes = "获取员工")
    @PostMapping(value = "/selectAll", consumes = "application/json")
    public Object selectAll(@RequestBody Map paramsMap,@RequestParam int currentPage, @RequestParam int pageSize) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        SalarySheetVo salarySheetVo = (SalarySheetVo) BeanUtils.mapToObject(paramsMap, SalarySheetVo.class);
        PageBean<SalarySheetVo> pageData = salarySheetService.selectAll(salarySheetVo, currentPage, pageSize);
        map.put("data", pageData);
        return map;
    }

    @ApiOperation(value = "删除单个", notes = "删除单个")
    @PostMapping(value = "/delete", consumes = "application/json")
    public Object delete(@RequestBody int[] ids) {
        int n = 0;
        for (int i : ids) {
            n = salarySheetService.deleteByPrimaryKey(i);
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
}
