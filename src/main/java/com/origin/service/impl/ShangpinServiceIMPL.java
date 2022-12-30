package com.origin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.origin.entity.Shangpin;
import com.origin.mapper.ShangpinMapper;
import com.origin.service.service01.ShangpinService;
import org.springframework.stereotype.Service;

@Service
public class ShangpinServiceIMPL extends ServiceImpl<ShangpinMapper, Shangpin> implements ShangpinService {
}
