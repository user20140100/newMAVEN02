package com.origin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.origin.entity.Fapiao;
import com.origin.mapper.FapiaoMapper;
import com.origin.service.service01.FapiaoService;
import org.springframework.stereotype.Service;

@Service
public class FapiaoServiceIMPL extends ServiceImpl<FapiaoMapper, Fapiao> implements FapiaoService {
}
