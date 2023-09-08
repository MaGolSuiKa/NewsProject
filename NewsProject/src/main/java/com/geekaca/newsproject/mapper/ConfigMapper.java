package com.geekaca.newsproject.mapper;

import com.geekaca.newsproject.domain.Config;

/**
* @author magol
* @description 针对表【tb_config】的数据库操作Mapper
* @createDate 2023-09-08 15:46:23
* @Entity com.geekaca.newsproject.domain.Config
*/
public interface ConfigMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);

}
