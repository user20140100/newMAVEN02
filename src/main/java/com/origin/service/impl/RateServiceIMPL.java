package com.origin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.origin.entity.Rate;
import com.origin.mapper.RateMapper;
import com.origin.service.service01.RateService;
import org.springframework.stereotype.Service;

@Service
public class RateServiceIMPL extends ServiceImpl<RateMapper, Rate> implements RateService {
}
