package com.geekaca.newsproject.mapper;

import com.geekaca.newsproject.domain.NewsCategory;

/**
* @author magol
* @description 针对表【tb_news_category】的数据库操作Mapper
* @createDate 2023-09-08 15:46:23
* @Entity com.geekaca.newsproject.domain.NewsCategory
*/
public interface NewsCategoryMapper {

    int deleteByPrimaryKey(Long id);

    int insert(NewsCategory record);

    int insertSelective(NewsCategory record);

    NewsCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewsCategory record);

    int updateByPrimaryKey(NewsCategory record);

}
