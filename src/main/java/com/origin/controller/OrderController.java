package com.origin.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.origin.common.R;
import com.origin.common.tool;
import com.origin.entity.Fapiao;
import com.origin.entity.Order;
import com.origin.entity.Shangpin;
import com.origin.entity.orsh;
import com.origin.mapper.OrderMapper;
import com.origin.service.service01.OrderService;
import com.origin.service.service01.ShangpinService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

//员工信息分页查询
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService o1;

    @Autowired
    private OrderMapper o2;

    @Autowired
    private ShangpinService s1;

//http://localhost:8081/order/page?page=1&pagesize=50&selecttype=2&begin=2022-11-07 00:00:00&over=2022-12-02 23:59:59





    //按页查询订单
    /*
    接口废弃！！！
     */
//    @GetMapping("/page")
//    public R<Page> orderlistpage(HttpServletRequest request ,@RequestParam int page, @RequestParam int pagesize,
//                                 @RequestParam(required = false)String goods, @RequestParam(required = false)int ratetype,
//                                 @RequestParam(required = false)String begin, @RequestParam(required = false)String over) throws ParseException {
//        System.out.println("订单列表请求信息："+page+" "+pagesize+" "+goods+" "+ratetype);
//        log.info("page = {},pageSize = {},goods={},ratetype = {}",page,pagesize,goods,ratetype);
//
//        //条件构造器
//        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
//        //分页构造器
//        Page pageInfo = new Page(page,pagesize);
//        //判断开始时间不能大于结束时间
//            if(!Objects.equals(begin, "") && !Objects.equals(over, "")){
//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date begin1 = format.parse(begin);
//                Date over1 = format.parse(over);
//                int comparetime = begin1.compareTo(over1);
//                if(comparetime != -1){
//                    return R.error("查询时间输入有误");
//                }
//            }
//                // 按用户名查询
////                queryWrapper.like(StringUtils.isNotEmpty(goods), Order::,goods);
//                // 按时间段查询
//                queryWrapper.ge(StringUtils.isNotEmpty(begin), Order::getDeclaretime,begin).le(StringUtils.isNotEmpty(over), Order::getDeclaretime,over);
//                //按税收开始时间排序
//                queryWrapper.orderByDesc(Order::getDeclaretime);
//                //执行查询
//                o1.page(pageInfo,queryWrapper);
//                if (pageInfo.getTotal() != 0){
//                    return R.success(pageInfo);
//                }
//        return R.error("无查询结果");
//    }


    @GetMapping("/page1")
    public R<Page> selectpage1(HttpServletRequest request, @RequestParam int current, @RequestParam int pagesize,
                               @RequestParam(required = false)String goods, @RequestParam(required = false, defaultValue="0")int stype,
                               @RequestParam(required = false)String begin, @RequestParam(required = false)String over) throws Exception {
//        current = 1;//当前第几页
//        pagesize = 4;//每页条数
//        goods = "testgoods002";
//        stime = "2022-11-20 00:00:00";
//        etime = "2022-11-24 23:59:59";
//        stype = 2;
//        goods = "";
//        begin = "";
//        over = "";
//        stype = 0;

        //判断开始时间不能大于结束时间
        if(!Objects.equals(begin, "") && !Objects.equals(over, "")){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(format.parse(begin).compareTo(format.parse(over)) != -1){
                return R.error("查询时间输入有误");
            }
        }

        List<orsh> orsh1 = o2.orshList(goods,stype,begin,over);
        if (orsh1.isEmpty()){
            return R.error("无查询结果");
        }
        int total = orsh1.size();
//        orsh1.forEach(System.out::println);
        List<Order> olist = new ArrayList<>();
        List<Shangpin> slist1 = new ArrayList<>();
        int temp = -10;
        Order temporder = new Order();
        int count = 1;
        for(orsh os:orsh1){
            Shangpin shangpin = new Shangpin();
            if (temp!=os.getFid()&&temp!=os.getOrderid()){
                if(temp!=-10){
                    temporder.setShangpinList(slist1);
                    Order order2 = temporder.deepcopy();
                    slist1.clear();
//                    System.out.println(count+" "+order+" "+order.getShangpinList());
                    olist.add(order2);
//                    System.out.println(olist);
                }
                temp = os.getOrderid();
                tool.tool2(temporder, os);
//                System.out.println("-----+++++-----");
//                System.out.println(order.toString());
                //                System.out.println(shangpin);
                //                System.out.println(slist1);
            }
            tool.tool1(shangpin, os);
            slist1.add(shangpin);
            if(count==total){
                tool.tool2(temporder, os);
                temporder.setShangpinList(slist1);
                Order order1 = temporder.deepcopy();
                slist1.clear();
                olist.add(order1);
            }
            count++;
        }
        Page<Order> page1 = new Page<>(current,pagesize,total);
        page1.setRecords(olist);
        if (page1.getRecords().isEmpty()){
            return R.error("当前分页无内容");
        }
//        System.out.println("/////=====/////");
        System.out.println(page1.getRecords());
        return R.success(page1);
    }



    @GetMapping("/page2")
    public R<Page> selectpage2(HttpServletRequest request, @RequestParam int current, @RequestParam int pagesize,
                               @RequestParam(required = false)String goods, @RequestParam(required = false, defaultValue="0")int stype,
                               @RequestParam(required = false)String begin, @RequestParam(required = false)String over) throws ParseException {
//        current = 1;//当前第几页
//        pagesize = 4;//每页条数
//        goods = "testgoods002";
//        begin = "2022-11-20 00:00:00";
//        over = "2022-11-24 23:59:59";
//        int stype = 2;
//        goods = "";
//        begin = "";
//        over = "";
//        stype = 0;

        String begin1;
        if (begin.isEmpty()){
            begin1 = "NULL";
        }
        else {
            begin1 = "'"+begin+"'";
        }
        String over1;
        if (over.isEmpty()){
            over1 = "NULL";
        }
        else {
            over1 = "'"+over+"'";
        }

        //判断开始时间不能大于结束时间
        if(!Objects.equals(begin, "") && !Objects.equals(over, "")){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(format.parse(begin).compareTo(format.parse(over)) != -1){
                return R.error("查询时间输入有误");
            }
        }

        String inValue = "SELECT fid\n" +
                "from shangpin01 JOIN order02 ON shangpin01.fid = order02.orderid\n" +
                "where (shangpin01.goods='" + goods + "' or '" + goods + "'='')\n" +
                "and (shangpin01.stype=" + stype + " or " + stype + "=0)\n" +
                "and (order02.declaretime >=" + begin1 + " or '" + begin + "'='')\n" +
                "and (order02.declaretime <=" + over1 + " or '" + over + "'='')";//按申报时间段，商品名称，税收类型查，为空时均默认按全部查询
        QueryWrapper<Order> qw = new QueryWrapper<Order>();
        qw.inSql("orderid", inValue);//子查询实现多表查询
        qw.orderByDesc("orderid");//按最近创建的订单优先来排序
        List<Order> listo = o1.list(qw);
        if (listo.isEmpty()){
            return R.error("无订单查询结果");
        }
//        listo.forEach(System.out::println);
        QueryWrapper<Shangpin> qw1 = new QueryWrapper<Shangpin>();
        qw1.inSql("fid", inValue);//查出包含这个goods的订单下的所有商品
        qw1.orderByDesc("fid");//按商品对应的订单的最近创建优先来排序
        List<Shangpin> lists = s1.list(qw1);
        if (lists.isEmpty()){
            return R.error("无商品查询结果");
        }
        CopyOnWriteArrayList<Shangpin> listc = new CopyOnWriteArrayList<>(lists);//优化涉及删除list中元素，要转换成线程安全的list类型
//        lists.forEach(System.out::println);
        List<Order> listp = new ArrayList<>();//新建list，存储当前页所需的订单
//        System.out.println("testlist");
        //把商品listc按订单号放入对应订单listo中
        int total = listo.size();
        int count = 1;//用于计算遍历到第几条订单
        for (Order order:listo){
            List<Shangpin> tl = new ArrayList<>();//设立temp，暂时存放每个订单的所有对应商品
//            System.out.println("orderid:"+order.getOrderid());
            for (Shangpin shangpin:listc){
                if (order.getOrderid()==shangpin.getFid()){//如果商品和订单对应，就放入temp中
                    tl.add(shangpin);
                    listc.remove(shangpin);//添加进temp后将lists中的这条商品数据移除
//                    System.out.println("fid:"+shangpin.getFid()+" goods:"+shangpin.getGoods());
                }
                else{//因为商品和订单的编号都是按相同顺序查出来的，遍历到第一次对应不上的时候，后续也都没有对应的情况了，就可以直接跳出循环
                    break;
                }
            }
            order.setShangpinList(tl);//把temp放入listo订单中
            if((current-1)*pagesize<count&&count<=current*pagesize){//第current条订单排在当前页中
                listp.add(order);//将订单放入当前页的list中
            }
            count++;
//            System.out.println("---------");
        }
        //新建一个page，把listo放入page中
        Page<Order> page = new Page<>(current,pagesize,total);
        page.setRecords(listp);
//        System.out.println("/////======//////");
        System.out.println(page.getRecords());
        if (page.getRecords().isEmpty()){
            return R.error("当前分页无内容");
        }
        return R.success(page);
    }

    //按id查询订单
    @GetMapping("/{orderid}")
    public R<Order> selectOrder(@PathVariable int orderid){
        log.info("根据id查询订单信息");
        Order order = o1.getById(orderid);
        if (order != null){
            return R.success(order);
        }
        return R.error("查询失败");
    }

    //更新订单
    @PutMapping("/update")
    public R<String> updateOrder(HttpServletRequest request,@RequestBody Order order){
        log.info(order.toString());

        long id = Thread.currentThread().getId();
        log.info("线程id为：{}",id);

        int orderid = (int)request.getSession().getAttribute("order");
//        Date now = new Date();
//        order.setUpdatetime(now);
        order.setOrderid(orderid);
        boolean bool = o1.updateById(order);
        if (bool){
            R.success("订单信息更改成功");
        }
        return R.error("信息更改失败");
    }

//    //新建订单
//    @PostMapping("/save")
//    public R<String> save(HttpServletRequest request, @RequestBody Order order){
////        System.out.println("打印order " + order.getRatesum() + order.getRatetype());
//        log.info("新增订单，订单信息：{}",order.toString());
//
//        long id = Thread.currentThread().getId();
//        log.info("线程id为：{}",id);
//
//        Object userid = request.getSession().getAttribute("Loginuserid");
//        System.out.println("输出Loginuserid：" + userid);
////        System.out.println("生成订单的姓名:" + username);
////        order.setUsername((String) user);
////        order.setUpdatetime(order.getStarttime());
////        System.out.println("输出时间："+order.getStarttime() + order.getEndtime());
//        boolean bool = o1.save(order);
//
//        if (bool){
//            return R.success("添加成功");
//        }
//        return R.error("添加失败");
//    }


    @PostMapping("/save")
    public R<String> Save(HttpServletRequest request, @RequestBody Order order){
        List<Shangpin> sl= order.getShangpinList();
        boolean bool = o1.save(order);//先存订单
        if(!bool){
            return R.error("发票信息保存成功");
        }
        LambdaQueryWrapper<Order> qw = new LambdaQueryWrapper<>();
        qw.eq(Order::getSdeclare,order.getSdeclare())
                .orderBy(true,false,Order::getOrderid)
                .last("limit 1");//取出最新一条数据
        Order order1 = o1.getOne(qw);//拿出刚刚存的订单
        System.out.println("输出新增订单的id:"+order1.getOrderid());
        if(!sl.isEmpty()){//
            int i=1;
            System.out.println("输出新增数据:");
            sl.forEach(System.out::println);
            for(Shangpin si:sl){
                i++;
                si.setFid(order1.getOrderid());
                System.out.println("输出商品信息:"+s1.toString() + i);
                boolean bool1 = s1.save(si);
                if(!bool1){
                    System.out.println("商品信息保存失败: "+i);
                    return R.error("商品信息保存失败: "+i);
                }
            }
        }
        else {
            return R.error("未填写商品信息 ");
        }
        return R.success("发票信息保存失败");
    }

}
