package com.origin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.origin.entity.Login;
import com.origin.mapper.LoginMapper;
import com.origin.service.service01.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceIMPL extends ServiceImpl<LoginMapper, Login> implements LoginService {
}
