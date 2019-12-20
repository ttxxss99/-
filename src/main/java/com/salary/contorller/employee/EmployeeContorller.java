package com.salary.contorller.employee;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
    public String update(HttpServletRequest request){
        String data = request.getParameter("data");
        Employee employee = (Employee) JSON.parse(data);
        int i = employeeService.update(employee);
        if(i>0){
            return "成功";
        }else{

            return "失败";
        }
    }

    @ApiOperation(value = "删除单个员工",notes = "删除单个员工")
    @PostMapping("/delete")
    public String delete(@RequestBody String s){
//        String data = request.getParameter("data");
        System.out.println(s.toString());
        JSONObject jsonObject = JSON.parseObject(s);
        List<Integer> integers = (List<Integer>) jsonObject.get("data");
//        List<Integer> integers = JSON.parseArray(s, Integer.class);
        Integer[] ids = new Integer[integers.size()];
        integers.toArray(ids);
        int n=0;
        for (int i: ids) {
            n = employeeService.delete(i);
        }
        if(n>0){
            return "成功";
        }else{
            return "失败";
        }
    }

    @ApiOperation(value = "增加单个员工",notes = "增加单个员工")
    @PostMapping("/insert")
    public String insert(HttpServletRequest request){
        String data = request.getParameter("data");
        Employee employee = JSON.parseObject(data, Employee.class);
        employeeService.insert(employee);
        int n=0;
        if(n>0){
            return "成功";
        }else{
            return "失败";
        }
    }
}
