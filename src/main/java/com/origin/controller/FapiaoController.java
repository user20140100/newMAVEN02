package com.origin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.origin.common.R;
import com.origin.entity.Fapiao;
import com.origin.entity.Shangpin;
import com.origin.service.service01.FapiaoService;
import com.origin.service.service01.ShangpinService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/fapiao")
public class FapiaoController {

    @Autowired
    private FapiaoService f1;

    @Autowired
    private ShangpinService s1;

    @PostMapping("/save")
    public R<String> Save(HttpServletRequest request, @RequestBody Fapiao fapiao){
        List<Shangpin> sl= fapiao.getShangpinList();
        if(!sl.isEmpty()){
            int i=1;
            for(Shangpin si:sl){
//                System.out.println(" getFid "+si.getFid()+" getGoods "+si.getGoods()+" getStype "
//                        +si.getStype()+" getAmount "+si.getAmount()+" getPrice "
//                        +si.getPrice()+" getSumprice "+si.getSumprice()+" getRatetype "
//                        +si.getRatetype()+" getTaxrate "+si.getTaxrate()+" getRatesum "
//                        +si.getRatesum()+"\n");
                i++;
                boolean bool = s1.save(si);
                if(!bool){
                    System.out.println("商品信息保存失败: "+i);
                    return R.error("商品信息保存失败: "+i);
                }
            }
        }

        boolean bool = f1.save(fapiao);
        if(bool){
            return R.success("发票信息保存成功");
        }
        return R.error("发票信息保存失败");
    }

    //根据发票代码查询发票信息
    @GetMapping("/{fapiaocode}")
    public R<Fapiao> selectfapiao(@PathVariable String fapiaocode){
        log.info("根据发票代码查询发票信息");
        LambdaQueryWrapper<Fapiao> qw = new LambdaQueryWrapper();
        qw.eq(Fapiao::getFapiaocode,fapiaocode);
        Fapiao fapiao = f1.getOne(qw);
        if (fapiao != null){
            return R.success(fapiao);
        }
        return R.error("查询失败");
    }

}
