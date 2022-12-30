package com.origin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@TableName("fapiao")
@Data
public class Fapiao implements Serializable {

    @TableId(type =  IdType.AUTO)
    private int id;

    private String fapiaocode;

    private String fapiaoren;

    private Date fapiaotime;

    private String address;

    private String phonenum;

    private String username;

    private String ratename;

    private String unit;

    private int countsum;

    private double unitprice;

    private double amount;

    private double ratesum;

    @TableField(exist = false)
    private List<Shangpin> shangpinList;

}
