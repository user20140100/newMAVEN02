package com.origin.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.*;
import java.util.Date;
import java.util.List;


@TableName("order02")
@Data
public class Order implements Serializable {

    @TableId(type = IdType.AUTO)
    private int orderid;

    private String sdeclare;

    private int userid;

    private String phonenum;

    private String bank;

    private int account;

    private Date declaretime;

    @TableField(exist = false)
    private List<Shangpin> shangpinList;

    public Order deepcopy() throws Exception{

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        oos.flush();

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (Order)ois.readObject();
    }

//    private String taxuser;
//
//    @TableField(fill = FieldFill.INSERT)
//    private Date starttime;
//
//    @TableField(fill = FieldFill.INSERT)
//    private String startuser;
//
//    @TableField(fill = FieldFill.INSERT_UPDATE)
//    private Date updatetime;
//
//    @TableField(fill = FieldFill.INSERT_UPDATE)
//    private String updateuser;
//
//    private Date endtime;
//
//    private Date ratestart;
//
//    private Date rateend;
//
//    private double taxrate;
//
//    private double ratesum;
//
//    private int ratetype;
//
//    private int orderstate;
//
//    private String remarks;

}
