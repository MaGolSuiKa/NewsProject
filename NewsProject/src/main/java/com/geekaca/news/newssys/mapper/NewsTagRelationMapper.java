package com.geekaca.news.newssys.mapper;


import com.geekaca.news.newssys.domain.NewsTagRelation;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface NewsTagRelationMapper {

    int deleteByPrimaryKey(Long id);

    int insert(NewsTagRelation record);

    int insertSelective(NewsTagRelation record);

    NewsTagRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewsTagRelation record);

    int updateByPrimaryKey(NewsTagRelation record);

}
