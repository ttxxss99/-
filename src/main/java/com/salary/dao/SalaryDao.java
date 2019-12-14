package com.salary.dao;

import com.salary.model.Salary;
import java.util.List;

public interface SalaryDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Salary
     *
     * @mbg.generated Sat Dec 14 11:41:05 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Salary
     *
     * @mbg.generated Sat Dec 14 11:41:05 CST 2019
     */
    int insert(Salary record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Salary
     *
     * @mbg.generated Sat Dec 14 11:41:05 CST 2019
     */
    Salary selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Salary
     *
     * @mbg.generated Sat Dec 14 11:41:05 CST 2019
     */
    List<Salary> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Salary
     *
     * @mbg.generated Sat Dec 14 11:41:05 CST 2019
     */
    int updateByPrimaryKey(Salary record);
}