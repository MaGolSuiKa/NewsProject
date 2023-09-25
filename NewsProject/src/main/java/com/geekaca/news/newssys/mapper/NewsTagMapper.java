package com.geekaca.news.newssys.mapper;


import com.geekaca.news.newssys.domain.NewsTag;
import com.geekaca.news.newssys.domain.TagNewsCount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface NewsTagMapper {

    int deleteByPrimaryKey(Long id);

    int insert(NewsTag record);

    int insertSelective(NewsTag record);

    NewsTag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewsTag record);

    int updateByPrimaryKey(NewsTag record);

    List<NewsTag> selectAll();

    int getTotalTags();

    List<TagNewsCount> selectTagNewsCounts();
}
