package com.geekaca.news.newssys.service;


import com.geekaca.news.newssys.domain.AdminUser;

public interface AdminUserService {

    AdminUser login(String userName, String password);
}
