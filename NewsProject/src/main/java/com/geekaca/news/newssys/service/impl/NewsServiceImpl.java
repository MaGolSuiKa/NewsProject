package com.geekaca.news.newssys.service.impl;

import com.geekaca.news.newssys.domain.News;
import com.geekaca.news.newssys.domain.NewsTag;
import com.geekaca.news.newssys.domain.NewsTagRelation;
import com.geekaca.news.newssys.mapper.NewsMapper;
import com.geekaca.news.newssys.mapper.NewsTagMapper;
import com.geekaca.news.newssys.mapper.NewsTagRelationMapper;
import com.geekaca.news.newssys.service.NewsService;
import com.geekaca.news.newssys.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private NewsTagMapper tagMapper;
    @Autowired
    private NewsTagRelationMapper tagRelationMapper;

    @Override
    public List<News> getAllNews() {
        return newsMapper.selectAll();
    }

    @Override
    public News getById(Long newsID) {
        News news = newsMapper.selectNewsAndCommentsById(newsID);
        return news;
    }

    @Override
    public PageResult getPageNews(Integer pageNO, Integer pageSize, String keyword) {
        int start = (pageNO - 1) * pageSize;
        List<News> newsList = newsMapper.selectByPage(start, pageSize, keyword);
        int count = newsMapper.selectNewsCount(keyword);
        PageResult pageResult = new PageResult(newsList, count, pageSize, pageNO);
        return pageResult;
    }

    @Override
    public boolean saveNews(News news) {

        int inserted = newsMapper.insertSelective(news);
        //查询出所有标签名字，id，放入HashMap 缓存便于使用
        List<NewsTag> newsTags1 = tagMapper.selectAll();
        Map<String, Integer> tagMap = new HashMap<>();
        for (int i = 0; i < newsTags1.size(); i++) {
            NewsTag newsTag = newsTags1.get(i);
            tagMap.put(newsTag.getTagName(), newsTag.getTagId());
        }
        //获取给定的所有标签
        String newsTags = news.getNewsTags();
        String[] tags = newsTags.split(",");
        for (int i = 0; i < tags.length; i++) {
            String tagName = tags[i];
            //发送sql 查询这个tag是否已经存在了
            //如果已经存在，获取对应的tagId,用来插入关联表
            Integer tagId = tagMap.get(tagName);
            if (tagId == null) {
                //如果不存在，说明是新的，插入标签表tb_news_tag, 获取DB生成的tag_id ,用来插入关联表
                NewsTag newsTag = new NewsTag();
                newsTag.setTagName(tagName);
                tagMapper.insertSelective(newsTag);
                //DB数据库生成的标签id
                tagId = newsTag.getTagId();
            }
            NewsTagRelation tagRelation = new NewsTagRelation();
            tagRelation.setNewsId(news.getNewsId());
            tagRelation.setTagId(tagId);
            tagRelationMapper.insertSelective(tagRelation);
        }
        return true;
    }

    @Override
    public int updateNewsViews(Long newsId) {
        int i = newsMapper.increateViews(newsId);
        return i;
    }

    @Override
    public int getTotalNews() {
        return newsMapper.getTotalNews();
    }
}
