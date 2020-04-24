package com.bishe.controller;

import com.bishe.model.Employee;
import com.bishe.service.EmployeeService;
import com.utils.BeanUtils;
import com.utils.PageBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @ApiOperation(value = "获取员工列表", notes = "获取所有员工列表")
    @GetMapping("/selectAll")
    public Object selectAll(int currentPage, int pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();

        PageBean<Employee> pageData = employeeService.selectAll(currentPage, pageSize);

        map.put("data", pageData);

        return map;
    }

    //测试
    @ApiOperation(value = "获取单个员工", notes = "获取员工")
    @PostMapping(value = "/selectByName", consumes = "application/json")
    public Object selectByName(@RequestBody Map paramsMap,@RequestParam int currentPage, @RequestParam int pageSize) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Employee employee = (Employee) BeanUtils.mapToObject(paramsMap, Employee.class);
        PageBean<Employee> pageData = employeeService.selectByPrimaryKey(employee, currentPage, pageSize);
        map.put("data", pageData);
        return map;
    }

    @ApiOperation(value = "修改单个员工", notes = "修改单个员工")
    @PostMapping(value = "/update", consumes = "application/json")
    public Object update(@RequestBody Map paramsMap) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        paramsMap.remove("time");
        Employee employee = (Employee) BeanUtils.mapToObject(paramsMap, Employee.class);
        if (null == employee.getName()) {
            map.put("data", "失败");
            return map;
        }
        int n = employeeService.updateByPrimaryKey(employee);
        if (n > 0) {
            map.put("data", "成功");
            return map;
        } else {
            map.put("data", "失败");
            return map;
        }
    }

    @ApiOperation(value = "删除单个员工", notes = "删除单个员工")
    @PostMapping(value = "/delete", consumes = "application/json")
    public Object delete(@RequestBody int[] ids) {
        int n = 0;
        for (int i : ids) {
            n = employeeService.deleteByPrimaryKey(i);
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

    @ApiOperation(value = "增加单个员工", notes = "增加单个员工")
    @PostMapping(value = "/insert", consumes = "application/json")
    public Object insert(@RequestBody Map paramsMap) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Employee employee = (Employee) BeanUtils.mapToObject(paramsMap, Employee.class);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        employee.setTime(new Date());
        if (employee.getName() == null) {
            map.put("data", "失败");
            return map;
        }
        int n = employeeService.insert(employee);

        if (n > 0) {
            map.put("data", "成功");
            return map;
        } else {
            map.put("data", "失败");
            return map;
        }
    }
}
