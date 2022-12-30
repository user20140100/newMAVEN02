package com.origin.common;

import com.origin.entity.Order;
import com.origin.entity.Shangpin;
import com.origin.entity.orsh;

public class tool {
    public static Shangpin tool1(Shangpin shangpin, orsh os){
        shangpin.setFid(os.getFid());
        shangpin.setSamount(os.getSamount());
        shangpin.setSsum(os.getSsum());
        shangpin.setRatesum(os.getRatesum());
        shangpin.setSname(os.getSname());
        shangpin.setStype(os.getStype());
        shangpin.setSdate(os.getSdate());
        shangpin.setGoods(os.getGoods());
        return shangpin;
    }
    public static Order tool2(Order order, orsh os){
        order.setUserid(os.getUserid());
        order.setBank(os.getBank());
        order.setAccount(os.getAccount());
        order.setOrderid(os.getOrderid());
        order.setSdeclare(os.getSdeclare());
        order.setDeclaretime(os.getDeclaretime());
        order.setPhonenum(os.getPhonenum());
        return order;
    }
}
