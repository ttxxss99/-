package com.bishe.controller;

import com.bishe.model.SalarySheet;
import com.bishe.service.SalarySheetService;
import com.utils.PageBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/SalarySheet")
public class SalarySheetController {

    @Autowired
    SalarySheetService salarySheetService;

    @ApiOperation(value = "获取列表")
    @GetMapping("/selectAll")
    public Object selectAll(int currentPage, int pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();

        PageBean<SalarySheet> pageData = salarySheetService.selectAll(currentPage,pageSize);

        map.put("data", pageData);

        return map;
    }

}
