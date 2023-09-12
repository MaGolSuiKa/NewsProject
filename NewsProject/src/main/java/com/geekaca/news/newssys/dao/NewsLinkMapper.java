package com.geekaca.news.newssys.dao;



import com.geekaca.news.newssys.domain.BlogLink;
import com.geekaca.news.newssys.utils.PageQueryUtil;

import java.util.List;

public interface NewsLinkMapper {
    int deleteByPrimaryKey(Integer linkId);

    int insert(BlogLink record);

    int insertSelective(BlogLink record);

    BlogLink selectByPrimaryKey(Integer linkId);

    int updateByPrimaryKeySelective(BlogLink record);

    int updateByPrimaryKey(BlogLink record);

    List<BlogLink> findLinkList(PageQueryUtil pageUtil);

    int getTotalLinks(PageQueryUtil pageUtil);

    int deleteBatch(Integer[] ids);
}