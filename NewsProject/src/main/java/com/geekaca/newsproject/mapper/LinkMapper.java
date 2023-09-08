package com.geekaca.newsproject.mapper;

import com.geekaca.newsproject.domain.Link;

/**
* @author magol
* @description 针对表【tb_link】的数据库操作Mapper
* @createDate 2023-09-08 15:46:23
* @Entity com.geekaca.newsproject.domain.Link
*/
public interface LinkMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Link record);

    int insertSelective(Link record);

    Link selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Link record);

    int updateByPrimaryKey(Link record);

}
