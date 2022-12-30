package com.origin.filter;


import com.alibaba.fastjson.JSON;
import com.origin.common.BaseContext;
import com.origin.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//检查用户是否已登录
@WebFilter(filterName = "logincheckfilter",urlPatterns = "/*")
@Slf4j
public class logincheckfilter implements Filter {
//路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requesturi = request.getRequestURI();

        log.info("拦截到请求：{}",request.getRequestURI());

        String[] urls = new String[]{
                "/user/login",
                "/user/logout",
                "/backend/**",
                "/front/**"
        };

        boolean check1 = check(urls,requesturi);

        if(check1){
            log.info("本次请求：{}",requesturi);
            filterChain.doFilter(request,response);
            return;
        }

        if(request.getSession().getAttribute("Loginuserid") != null){
            log.info("用户已登录，用户id为：{},用户名为：{}",request.getSession().getAttribute("Loginuserid"),request.getSession().getAttribute("Loginusername"));

            String usernametemp = (String) request.getSession().getAttribute("Loginusername");
            BaseContext.setCurrentuser(usernametemp);

            long id = Thread.currentThread().getId();
            log.info("线程id为：{}",id);




            filterChain.doFilter(request,response);
            return;
        }


        log.info("用户未登录");
        //未登录时通过输出流方式向客户端相应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
//        log.info("拦截到请求：{}",request.getRequestURI());
//        filterChain.doFilter(request,response);

    }

    public boolean check(String[] urls,String requesturi){
        for(String url : urls){
            boolean match = PATH_MATCHER.match(url,requesturi);
            if(match){
                return true;
            }
        }
        return false;
    }
}
