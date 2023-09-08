package com.geekaca.newsproject.mapper;

import com.geekaca.newsproject.domain.News;

/**
* @author magol
* @description 针对表【tb_news】的数据库操作Mapper
* @createDate 2023-09-08 15:46:23
* @Entity com.geekaca.newsproject.domain.News
*/
public interface NewsMapper {

    int deleteByPrimaryKey(Long id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

}
