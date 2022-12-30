package com.origin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.*;
import java.util.Date;

@TableName("shangpin01")
@Data
public class Shangpin implements Serializable {

    private int fid;

    private String goods;

    private String sname;

    private int samount;

    private double ssum;

    private Date sdate;

    private int stype;

    private double ratesum;

    public Shangpin deepcopy() throws Exception{

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        oos.flush();

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (Shangpin)ois.readObject();
    }

//    private String stype;
//
//    private int amount;
//
//    private double price;
//
//    private double sumprice;
//
//    private int ratetype;
//
//    private double taxrate;
//
//    private double ratesum;

}
