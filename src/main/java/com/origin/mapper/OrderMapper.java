package com.origin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.origin.entity.Order;
import com.origin.entity.orsh;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    @Select("SELECT *\n" +
            "FROM order02\n" +
            "JOIN shangpin01 ON order02.orderid = shangpin01.fid\n" +
            "WHERE(orderid IN (\n" +
            "SELECT fid\n" +
            "FROM shangpin01 JOIN order02 ON shangpin01.fid = order02.orderid\n" +
            "WHERE(shangpin01.goods = #{goods}OR #{goods} = '')\n" +
            "AND (shangpin01.stype = #{stype} OR #{stype} = 0)\n" +
            "AND (order02.declaretime >= #{begin}OR #{begin} = '')\n" +
            "AND (order02.declaretime <= #{over}OR #{over} = '')))\n" +
            "ORDER BY orderid DESC")
    List<orsh> orshList(String goods, int stype, String begin, String over);

}
