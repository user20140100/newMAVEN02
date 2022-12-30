package com.origin.controller;

import com.origin.common.R;
import com.origin.entity.Rate;
import com.origin.service.service01.RateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/Message")
public class con1 {

    @Autowired
    public RateService o1;
    @PostMapping("/ShowGroupFrameServlet")
    public R<String> test1(HttpServletRequest request, @RequestBody String str){
        System.out.println(str);
        return null;
    }

}
