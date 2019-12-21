package com.salary.contorller.employee;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.salary.model.Employee;
import com.salary.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeContorller {
    @Autowired
    EmployeeService employeeService;

    @ApiOperation(value = "获取员工列表",notes = "获取所有员工列表")
    @GetMapping("/selectAll")
    public Object selectAll(){
        List<Employee> employees = employeeService.selectAll();
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("data",employees);
        return map;
    }

    @ApiOperation(value = "获取单个员工",notes = "获取员工")
    @PostMapping("/selectByName")
    public Object selectByName(HttpServletRequest request){
        String data = request.getParameter("data");
        int i = Integer.getInteger(data);
        Employee employee = employeeService.selectOne(i);
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("data",employee);
        return map;
    }

    @ApiOperation(value = "修改单个员工",notes = "修改单个员工")
    @PostMapping("/update")
    public Object update(HttpServletRequest request){
        String data = request.getParameter("data");
        Employee employee = (Employee) JSON.parse(data);
        int i = employeeService.update(employee);
        Map<String,Object> map =new HashMap<String,Object>();
        if(i>0){
            map.put("data","成功");
            return map;
        }else{
            map.put("data","失败");
            return map;
        }
    }

    @ApiOperation(value = "删除单个员工",notes = "删除单个员工")
    @PostMapping("/delete")
    public Object delete(@RequestBody String s){
        System.out.println(s.toString());
        JSONObject jsonObject = JSON.parseObject(s);
        List<Integer> integers = (List<Integer>) jsonObject.get("data");
        Integer[] ids = new Integer[integers.size()];
        integers.toArray(ids);
        int n=0;
        for (int i: ids) {
            n = employeeService.delete(i);
        }
        Map<String,Object> map =new HashMap<String,Object>();
        if(n>0){
            map.put("data","成功");
            return map;
        }else{
            map.put("data","失败");
            return map;
        }
    }

    @ApiOperation(value = "增加单个员工",notes = "增加单个员工")
    @PostMapping("/insert")
    public Object insert(@RequestBody JSONObject s){
        System.out.println(s.toString());
        Map<String,Object> map =new HashMap<String,Object>();
        Employee employee = s.getObject("data", Employee.class);
        if(employee.geteName()==null){
            map.put("data","失败");
            return map;
        }
        int n=employeeService.insert(employee);;
        if(n>0){
            map.put("data","成功");
            return map;
        }else{
            map.put("data","失败");
            return map;
        }
    }
}
