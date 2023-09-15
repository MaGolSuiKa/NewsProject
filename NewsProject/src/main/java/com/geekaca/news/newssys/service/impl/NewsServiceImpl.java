package com.geekaca.news.newssys.service.impl;

import com.geekaca.news.newssys.dao.NewsMapper;
import com.geekaca.news.newssys.domain.News;
import com.geekaca.news.newssys.service.NewsService;
import com.geekaca.news.newssys.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<News> getAllNews() {
        return newsMapper.selectAll();
    }

    @Override
    public News getById(Long newsID) {
        News news = newsMapper.selectById(newsID);
        return news;
    }

    @Override
    public PageResult getPageNews(Integer pageNO, Integer pageSize) {
        int start = (pageNO - 1) * pageSize;
        List<News> newsList = newsMapper.selectByPage(start, pageSize);
        int count = newsMapper.selectNewsCount();
        PageResult pageResult = new PageResult(newsList, count, pageSize, pageNO);
        return pageResult;
    }
}
