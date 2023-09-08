package com.geekaca.newsproject.mapper;

import com.geekaca.newsproject.domain.NewsTagRelation;

/**
* @author magol
* @description 针对表【tb_news_tag_relation】的数据库操作Mapper
* @createDate 2023-09-08 15:46:23
* @Entity com.geekaca.newsproject.domain.NewsTagRelation
*/
public interface NewsTagRelationMapper {

    int deleteByPrimaryKey(Long id);

    int insert(NewsTagRelation record);

    int insertSelective(NewsTagRelation record);

    NewsTagRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewsTagRelation record);

    int updateByPrimaryKey(NewsTagRelation record);

}
