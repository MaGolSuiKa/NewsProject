package com.geekaca.news.newssys.service.impl;

import com.geekaca.news.newssys.domain.TagNewsCount;
import com.geekaca.news.newssys.mapper.NewsTagMapper;
import com.geekaca.news.newssys.service.LinkService;
import com.geekaca.news.newssys.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Resource
    private NewsTagMapper newsTagMapper;
    @Override
    public int getTotalTags() {
        return newsTagMapper.getTotalTags();
    }

    @Override
    public List<TagNewsCount> getAll() {
        return newsTagMapper.selectTagNewsCounts();
    }
}
