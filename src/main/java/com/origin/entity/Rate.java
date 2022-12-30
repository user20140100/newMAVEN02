package com.origin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("rate01")
@Data
public class Rate implements Serializable {

    @TableId(type =  IdType.AUTO)
    private int rateid;

    private String username;

    private int ratetype;

    private double taxrate;

    private double ratesum;

    private double paidin;

    private double credit;

    private double refund;

    private Date ratestart;

    private Date rateend;

    private int ratestate;

}
