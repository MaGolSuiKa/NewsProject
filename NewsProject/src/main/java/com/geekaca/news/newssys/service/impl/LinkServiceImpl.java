package com.geekaca.news.newssys.service.impl;

import com.geekaca.news.newssys.mapper.BlogLinkMapper;
import com.geekaca.news.newssys.service.CategoryService;
import com.geekaca.news.newssys.service.LinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LinkServiceImpl implements LinkService {
    @Resource
    private BlogLinkMapper linkMapper;
    @Override
    public int getTotalLinks() {
        return linkMapper.getTotalLinks();
    }
}
