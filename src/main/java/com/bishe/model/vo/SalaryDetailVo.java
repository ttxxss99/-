package com.bishe.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SalaryDetailVo {

   private Integer id;

   private Integer eId;

   public Integer geteId() {
      return eId;
   }

   public void seteId(Integer eId) {
      this.eId = eId;
   }

   public Integer getpId() {
      return pId;
   }

   public void setpId(Integer pId) {
      this.pId = pId;
   }

   private double day;

   public double getDay() {
      return day;
   }

   public void setDay(double day) {
      this.day = day;
   }

   private Integer pId;

   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
   private Date time;

   private Integer fine;

   private Integer logicDel;

   private String eName;

   private String pName;

   public Integer getFine() {
      return fine;
   }

   public void setFine(Integer fine) {
      this.fine = fine;
   }

   //以下属性是DO作用
   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
   private Date beginTime;

   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
   private Date endTime;

}
