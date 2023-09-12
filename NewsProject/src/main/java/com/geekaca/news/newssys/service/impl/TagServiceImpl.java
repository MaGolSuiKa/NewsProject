package com.geekaca.news.newssys.service.impl;


import com.geekaca.news.newssys.dao.NewsTagMapper;
import com.geekaca.news.newssys.dao.NewsTagRelationMapper;
import com.geekaca.news.newssys.domain.BlogTagCount;
import com.geekaca.news.newssys.domain.NewsTag;
import com.geekaca.news.newssys.service.TagService;
import com.geekaca.news.newssys.utils.PageQueryUtil;
import com.geekaca.news.newssys.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private NewsTagMapper newsTagMapper;
    @Autowired
    private NewsTagRelationMapper relationMapper;

    @Override
    public PageResult getBlogTagPage(PageQueryUtil pageUtil) {
        List<NewsTag> tags = newsTagMapper.findTagList(pageUtil);
        int total = newsTagMapper.getTotalTags(pageUtil);
        PageResult pageResult = new PageResult(tags, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public int getTotalTags() {
        return newsTagMapper.getTotalTags(null);
    }

    @Override
    public Boolean saveTag(String tagName) {
        NewsTag temp = newsTagMapper.selectByTagName(tagName);
        if (temp == null) {
            NewsTag blogTag = new NewsTag();
            blogTag.setTagName(tagName);
            return newsTagMapper.insertSelective(blogTag) > 0;
        }
        return false;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        //已存在关联关系不删除
        List<Long> relations = relationMapper.selectDistinctTagIds(ids);
        if (!CollectionUtils.isEmpty(relations)) {
            return false;
        }
        //删除tag
        return newsTagMapper.deleteBatch(ids) > 0;
    }

    @Override
    public List<BlogTagCount> getBlogTagCountForIndex() {
        return newsTagMapper.getTagCount();
    }
}
