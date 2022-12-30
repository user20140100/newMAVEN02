package com.origin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("login")
@Data
public class Login implements Serializable {

    @TableId(type =  IdType.AUTO)
    private int id;

    private String username;

    private String password;

    private String xingming;

    private String shenfenzheng;

    private String address;

    private String phonenum;

    private int usertype;

    private int userstate;

}
