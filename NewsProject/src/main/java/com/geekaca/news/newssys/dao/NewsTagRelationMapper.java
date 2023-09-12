package com.geekaca.news.newssys.dao;

import com.geekaca.news.newssys.domain.NewsTagRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsTagRelationMapper {
    int deleteByPrimaryKey(Long relationId);

    int insert( NewsTagRelation record);

    int insertSelective( NewsTagRelation record);

    NewsTagRelation selectByPrimaryKey(Long relationId);

    NewsTagRelation selectByBlogIdAndTagId(@Param("blogId") Long blogId, @Param("tagId") Integer tagId);

    List<Long> selectDistinctTagIds(Integer[] tagIds);

    int updateByPrimaryKeySelective( NewsTagRelation record);

    int updateByPrimaryKey( NewsTagRelation record);

    int batchInsert(@Param("relationList") List< NewsTagRelation> blogTagRelationList);

    int deleteByBlogId(Long blogId);
}