package com.origin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.origin.common.R;
import com.origin.entity.Barlist6;
import com.origin.entity.Pielist6;
import com.origin.entity.Totalmoneybyrate;
import com.origin.entity.listmounth;
import com.origin.mapper.ShangpinMapper;
import com.origin.service.service01.ShangpinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/shangpin")
public class ShangpinController {

    @Autowired
    private ShangpinService s1;

    @Autowired
    private ShangpinMapper s2;

    //按月查询商品总税额
    @PostMapping("/ratesumbymounth")
    public R<listmounth> selectmouth(HttpServletRequest request){
        listmounth lm = new listmounth();
        List<Double> listmounth = s2.selectbymounth();
        listmounth.forEach(System.out::println);
        lm.setCountbylist(listmounth);
        return R.success(lm);
    }

    //查询柱状图今年总销量按税种分类
    @PostMapping("/totalmoneybyrate")
    public R<Barlist6> selectbar(HttpServletRequest request){
        Barlist6 barlist6 = new Barlist6();
        List<Double> l1 = new ArrayList<>();
        List<Double> l2 = new ArrayList<>();
        List<Double> l3 = new ArrayList<>();
        List<Double> l4 = new ArrayList<>();
        List<Double> l5 = new ArrayList<>();
        List<Double> l6 = new ArrayList<>();
        List<Totalmoneybyrate> totalmoneybyrateList = s2.selectbar();
        totalmoneybyrateList.forEach(System.out::println);
        for(Totalmoneybyrate tr : totalmoneybyrateList){
            double r1=tr.getTotalmoney1();
            double r2=tr.getTotalmoney2();
            double r3=tr.getTotalmoney3();
            double r4=tr.getTotalmoney4();
            double r5=tr.getTotalmoney5();
            double r6=tr.getTotalmoney6();
            l1.add(r1);
            l2.add(r2);
            l3.add(r3);
            l4.add(r4);
            l5.add(r5);
            l6.add(r6);
        }
        barlist6.setRate1(l1);
        barlist6.setRate2(l2);
        barlist6.setRate3(l3);
        barlist6.setRate4(l4);
        barlist6.setRate5(l5);
        barlist6.setRate6(l6);
        return R.success(barlist6);
    }

    @PostMapping("/totalratesumbystype")
    public R<Pielist6> selectpie(HttpServletRequest request){
        Pielist6 pielist6 = new Pielist6();
        List<Double> listpie = s2.selectbystype();
        listpie.forEach(System.out::println);
        pielist6.setPielist(listpie);
        return R.success(pielist6);
    }

}
