package com.geekaca.news.newssys.mapper;


import com.geekaca.news.newssys.domain.BlogLink;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogLinkMapper {
    int deleteByPrimaryKey(Integer linkId);

    int insert(BlogLink record);

    int insertSelective(BlogLink record);

    BlogLink selectByPrimaryKey(Integer linkId);

    int updateByPrimaryKeySelective(BlogLink record);

    int updateByPrimaryKey(BlogLink record);


    int getTotalLinks();

    int deleteBatch(Integer[] ids);
}