package com.salary.dao;

import com.salary.model.Vo.PlaceListVo;
import com.salary.model.Place;
import java.util.List;

public interface PlaceDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Place
     *
     * @mbg.generated Sun Dec 15 16:41:20 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Place
     *
     * @mbg.generated Sun Dec 15 16:41:20 CST 2019
     */
    int insert(Place record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Place
     *
     * @mbg.generated Sun Dec 15 16:41:20 CST 2019
     */
    Place selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Place
     *
     * @mbg.generated Sun Dec 15 16:41:20 CST 2019
     */
    List<PlaceListVo> selectAll();
    List<PlaceListVo>  selectByName(PlaceListVo placeListVo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Place
     *
     * @mbg.generated Sun Dec 15 16:41:20 CST 2019
     */
    int updateByPrimaryKey(Place record);
}