package com.geekaca.news.newssys.service;

import com.geekaca.news.newssys.domain.News;
import com.geekaca.news.newssys.utils.PageResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface NewsService {

    List<News> getAllNews();

    News getById(Long newsID);

    //查询新闻列表，带有分页数据
    PageResult getPageNews(Integer pageNO, Integer pageSize, String keyword);

    boolean saveNews(News news);

    //把文章的访问量+1
    int updateNewsViews(Long newsId);
}
