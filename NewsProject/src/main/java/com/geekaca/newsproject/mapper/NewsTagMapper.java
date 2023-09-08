package com.geekaca.newsproject.mapper;

import com.geekaca.newsproject.domain.NewsTag;

/**
* @author magol
* @description 针对表【tb_news_tag】的数据库操作Mapper
* @createDate 2023-09-08 15:46:23
* @Entity com.geekaca.newsproject.domain.NewsTag
*/
public interface NewsTagMapper {

    int deleteByPrimaryKey(Long id);

    int insert(NewsTag record);

    int insertSelective(NewsTag record);

    NewsTag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewsTag record);

    int updateByPrimaryKey(NewsTag record);

}
