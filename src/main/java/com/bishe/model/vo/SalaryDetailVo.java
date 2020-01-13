package com.bishe.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SalaryDetailVo {

   private Integer id;

   private Integer eId;

   private Integer day;

   private Integer pId;

   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
   private Date time;

   private Integer fine;

   private Integer logicDel;

   private String eName;

   private String pName;




   //以下属性是DO作用
   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
   private Date beginTime;

   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
   private Date endTime;

}
