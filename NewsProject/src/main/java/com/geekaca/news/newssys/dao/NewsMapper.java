package com.geekaca.news.newssys.dao;

import com.geekaca.news.newssys.domain.News;
import com.geekaca.news.newssys.utils.PageInfo;
import com.geekaca.news.newssys.utils.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsMapper {
    int deleteByPrimaryKey(Long NewsId);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Long NewsId);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKeyWithBLOBs(News record);

    int updateByPrimaryKey(News record);

    /**
     * bad smell 不好的气味
     * @param pageUtil
     * @return
     */
    List<News> findNewsList(PageQueryUtil pageUtil);

    List<News> findNewsListByType(@Param("type") int type, @Param("limit") int limit);

    int getTotalNewss(PageQueryUtil pageUtil);

    int deleteBatch(Integer[] ids);

    List<News> getNewssPageByTagId(PageInfo pageUtil);

    int getTotalNewssByTagId(PageInfo pageUtil);

    News selectBySubUrl(String subUrl);

    int updateNewsCategorys(@Param("categoryName") String categoryName, @Param("categoryId") Integer categoryId, @Param("ids")Integer[] ids);

    List<News> selectAll();
    News selectById(Long id);
    List<News> selectByPage(@Param("start") Integer start, @Param("recordSize") Integer recordSize);
    int selectNewsCount();
}
