package com.geekaca.news.newssys.dao;


import com.geekaca.news.newssys.domain.NewsCategory;
import com.geekaca.news.newssys.utils.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogCategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(NewsCategory record);

    int insertSelective(NewsCategory record);

    NewsCategory selectByPrimaryKey(Integer categoryId);

    NewsCategory selectByCategoryName(String categoryName);

    int updateByPrimaryKeySelective(NewsCategory record);

    int updateByPrimaryKey(NewsCategory record);

    List<NewsCategory> findCategoryList(PageQueryUtil pageUtil);

    List<NewsCategory> selectByCategoryIds(@Param("categoryIds") List<Integer> categoryIds);

    int getTotalCategories(PageQueryUtil pageUtil);

    int deleteBatch(Integer[] ids);
}