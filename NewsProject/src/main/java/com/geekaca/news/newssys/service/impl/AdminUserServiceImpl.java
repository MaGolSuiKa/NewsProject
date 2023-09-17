package com.geekaca.news.newssys.service.impl;

import com.geekaca.news.newssys.domain.AdminUser;
import com.geekaca.news.newssys.mapper.AdminUserMapper;
import com.geekaca.news.newssys.service.AdminUserService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(String userName, String password) {
        AdminUser user = adminUserMapper.login(userName, password);
        return user;
    }

}
