package com.origin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.origin.entity.Order;
import com.origin.mapper.OrderMapper;
import com.origin.service.service01.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceIMPL extends ServiceImpl<OrderMapper, Order> implements OrderService {
}
