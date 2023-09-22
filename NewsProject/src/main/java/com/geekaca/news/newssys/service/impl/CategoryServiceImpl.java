package com.geekaca.news.newssys.service.impl;
import com.geekaca.news.newssys.mapper.NewsCategoryMapper;
import com.geekaca.news.newssys.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private NewsCategoryMapper categoryMapper;

    @Override
    public int getTotalCategories() {
        return categoryMapper.getTotalCategories();
    }
}
