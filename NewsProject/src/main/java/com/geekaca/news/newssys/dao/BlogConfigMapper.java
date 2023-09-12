package com.geekaca.news.newssys.dao;

import com.geekaca.news.newssys.domain.BlogConfig;


import java.util.List;

public interface BlogConfigMapper {
    List<BlogConfig> selectAll();

    BlogConfig selectByPrimaryKey(String configName);

    int updateByPrimaryKeySelective(BlogConfig record);

}