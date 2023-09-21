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

    // 前台首页 新闻列表查询
    List<News> selectAll();

    News selectNewsAndCommentsById(Long id);

    List<News> selectByPage(@Param("start") Integer start, @Param("recordSize") Integer recordSize, @Param("keyword") String keyword);
    int selectNewsCount(@Param("keyword") String keyword);

    int increateViews(@Param("newsId") Long newsId);
}
