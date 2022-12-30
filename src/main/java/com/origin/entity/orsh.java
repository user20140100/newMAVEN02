package com.origin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class orsh implements Serializable {

    private int orderid;

    private String sdeclare;

    private int userid;

    private String phonenum;

    private String bank;

    private int account;

    private Date declaretime;

    private int fid;

    private String goods;

    private String sname;

    private int samount;

    private double ssum;

    private Date sdate;

    private int stype;

    private double ratesum;

}
