package com.geekaca.news.newssys.mapper;


import com.geekaca.news.newssys.domain.NewsCategory;

import java.util.List;

public interface NewsCategoryMapper {

    int deleteByPrimaryKey(Long id);

    int insert(NewsCategory record);

    int insertSelective(NewsCategory record);

    NewsCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewsCategory record);

    int updateByPrimaryKey(NewsCategory record);

    List<NewsCategory> findAll();

    int getTotalCategories();
}
