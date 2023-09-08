package com.geekaca.newsproject.mapper;

import com.geekaca.newsproject.domain.AdminUser;

/**
* @author magol
* @description 针对表【tb_admin_user】的数据库操作Mapper
* @createDate 2023-09-08 15:46:23
* @Entity com.geekaca.newsproject.domain.AdminUser
*/
public interface AdminUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AdminUser record);

    int insertSelective(AdminUser record);

    AdminUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminUser record);

    int updateByPrimaryKey(AdminUser record);

}
