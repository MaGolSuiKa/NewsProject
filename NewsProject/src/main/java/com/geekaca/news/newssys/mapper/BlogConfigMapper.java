package com.geekaca.news.newssys.mapper;



import com.geekaca.news.newssys.domain.BlogConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogConfigMapper {
    List<BlogConfig> selectAll();

    BlogConfig selectByPrimaryKey(String configName);

    int updateByPrimaryKeySelective(BlogConfig record);

}