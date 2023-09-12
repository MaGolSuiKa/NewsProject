package com.geekaca.news.newssys.dao;



import com.geekaca.news.newssys.domain.BlogTagCount;
import com.geekaca.news.newssys.domain.NewsTag;
import com.geekaca.news.newssys.utils.PageQueryUtil;

import java.util.List;

public interface NewsTagMapper {
    int deleteByPrimaryKey(Integer tagId);

    int insert(NewsTag record);

    int insertSelective(NewsTag record);

    NewsTag selectByPrimaryKey(Integer tagId);

    NewsTag selectByTagName(String tagName);

    int updateByPrimaryKeySelective(NewsTag record);

    int updateByPrimaryKey(NewsTag record);

    List<NewsTag> findTagList(PageQueryUtil pageUtil);

    List<BlogTagCount> getTagCount();

    int getTotalTags(PageQueryUtil pageUtil);

    int deleteBatch(Integer[] ids);

    int batchInsertBlogTag(List<NewsTag> tagList);
}