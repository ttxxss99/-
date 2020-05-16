package com.bishe.service.Impl;

import com.bishe.dao.*;
import com.bishe.model.*;
import com.bishe.model.vo.SalaryDetailVo;
import com.bishe.service.SalaryDetailService;
import com.github.pagehelper.PageHelper;
import com.utils.PageBean;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SalaryDetailServiceImpl implements SalaryDetailService {

    @Autowired
    SalaryDetailDao salaryDetailDao;

    @Autowired
    SalarySheetDao salarySheetDao;

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    PostDao postDao;

    @Autowired
    PayStructureDao payStructureDao;

    private static Map workAgeMap = new HashMap<Integer,Integer>(){{
        put(0,500);put(1,1000);put(2,3000);put(3,5000);put(4,7000);put(5,10000);
    }};

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return salaryDetailDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SalaryDetail salaryDetail) throws InvocationTargetException, IllegalAccessException {
        SalaryDetailVo salaryDetailVo = new SalaryDetailVo();
        BeanUtils.copyProperties(salaryDetailVo,salaryDetail);
        salaryDetail.setTime(new Date());
        int i = salaryDetailDao.insert(salaryDetail);
        SalarySheet salarySheet1 = salarySheetDao.selectByPrimaryKey(salaryDetail.geteId());
        Post post = postDao.selectByPrimaryKey(salaryDetail.getpId());
        if(null==salarySheet1){

            Employee employee =   new Employee();
            employee.setId(salaryDetail.geteId());
            List<Employee> employees = employeeDao.selectByPrimaryKey(employee);
            employee = employees.get(0);

            PayStructure payStructure = payStructureDao.selectByPrimaryKey(post.getpSId());
            List<SalaryDetailVo> salaryDetailVos = salaryDetailDao.selectByPrimaryKey(salaryDetailVo);
            int sum = salaryDetailVos.stream().mapToInt(SalaryDetailVo::getFine).sum();//借款
            double day = salaryDetailVos.stream().mapToDouble(SalaryDetailVo::getDay).sum();//总天数

            SalarySheet salarySheet = new SalarySheet();

            salarySheet.seteId(salaryDetail.geteId());
            salarySheet.setDay(day);
            salarySheet.setpSId(post.getpSId());
            salarySheet.setTime(new Date());

            salarySheet.setMoney(post.getMoney()+payStructure.getBasic()+(int)workAgeMap.get(employee.getWorkAge())-sum);


            salarySheetDao.insert(salarySheet);
        }else {

            salarySheet1.setDay(salarySheet1.getDay()+salaryDetail.getDay());
            salarySheet1.setTime(new Date());
            //应该还有日工资*天数的钱
            salarySheet1.setMoney(salarySheet1.getMoney()-salaryDetail.getFine()+(int)salaryDetail.getDay()*post.getMoney());
            salarySheetDao.updateByPrimaryKey(salarySheet1);
        }
        return i;
    }

    @Override
    public PageBean<SalaryDetailVo> selectByPrimaryKey(SalaryDetailVo salaryDetailVo,int currentPage, int pageSize) {
        int i =  salaryDetailDao.selectByPrimaryKey(salaryDetailVo).size();
        PageHelper.startPage(currentPage, pageSize);
        List<SalaryDetailVo> salaryDetailVos = salaryDetailDao.selectByPrimaryKey(salaryDetailVo);
        PageBean<SalaryDetailVo> pageData = new PageBean<>(currentPage, pageSize, salaryDetailVos.size());
        pageData.setItems(salaryDetailVos);
        pageData.setTotalNum(i);
        return pageData;
    }

    @Override
    public PageBean<SalaryDetailVo> selectAll(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<SalaryDetailVo> salaryDetailVos = salaryDetailDao.selectAll();
        PageBean<SalaryDetailVo> pageData = new PageBean<>(currentPage, pageSize, salaryDetailVos.size());
        pageData.setItems(salaryDetailVos);
        return pageData;
    }

    @Override
    public int updateByPrimaryKey(SalaryDetail salaryDetail) {
        int i = salaryDetailDao.updateByPrimaryKey(salaryDetail);
        SalarySheet salarySheet = salarySheetDao.selectByPrimaryKey(salaryDetail.geteId());


        return i;
    }
}
