package com.geekaca.news.newssys.mapper;

import com.geekaca.news.newssys.domain.NewsComment;
import com.geekaca.news.newssys.utils.PageBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface NewsCommentMapper {

    int deleteByPrimaryKey(Long id);

    int insert(NewsComment record);

    int insertSelective(NewsComment record);

    NewsComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewsComment record);

    int updateByPrimaryKey(NewsComment record);

    List<NewsComment> findNewsCommentList(PageBean pageBean);

    Boolean checkDone(Integer[] ids);

    int deleteBatch(Integer[] ids);
}
