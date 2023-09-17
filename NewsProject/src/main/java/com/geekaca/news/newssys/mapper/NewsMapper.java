package com.geekaca.news.newssys.mapper;


import com.geekaca.news.newssys.domain.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface NewsMapper {

    int deleteByPrimaryKey(Long id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);


    List<News> selectAll();
    News selectNewsAndCommentsById(Long id);

    List<News> selectByPage(@Param("start") Integer start, @Param("recordSize") Integer recordSize);
    int selectNewsCount();

    int increateViews(@Param("newsId") Long newsId);
}
