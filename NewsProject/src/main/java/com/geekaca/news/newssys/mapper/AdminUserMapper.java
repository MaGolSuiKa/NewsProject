package com.geekaca.news.newssys.mapper;

import com.geekaca.news.newssys.domain.AdminUser;
import org.apache.ibatis.annotations.Param;


public interface AdminUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);
    //登陆
    AdminUser login(@Param("uname") String username, @Param("passwd") String password);
}
