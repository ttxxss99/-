package com.salary.contorller.salary;

import com.salary.model.Salary;
import com.salary.service.Impl.SalaryServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "工资模块",description = "工资模块接口")
public class SalaryController {

    @Autowired
    SalaryServiceImpl salaryService;

    public void insert(){

    }

    @ApiOperation(value = "获取工资列表",notes = "获取所有工资列表")
    @GetMapping("/salary")
    public Object selectAll(){

        return salaryService.selectAll();
    }
}
