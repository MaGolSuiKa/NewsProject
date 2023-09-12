package com.geekaca.news.newssys.service.impl;


import com.geekaca.news.newssys.dao.BlogCategoryMapper;
import com.geekaca.news.newssys.dao.NewsMapper;
import com.geekaca.news.newssys.domain.NewsCategory;
import com.geekaca.news.newssys.service.CategoryService;
import com.geekaca.news.newssys.utils.PageQueryUtil;
import com.geekaca.news.newssys.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public PageResult getBlogCategoryPage(PageQueryUtil pageUtil) {
        List<NewsCategory> categoryList = blogCategoryMapper.findCategoryList(pageUtil);
        int total = blogCategoryMapper.getTotalCategories(pageUtil);
        PageResult pageResult = new PageResult(categoryList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public int getTotalCategories() {
        return blogCategoryMapper.getTotalCategories(null);
    }

    @Override
    public Boolean saveCategory(String categoryName, String categoryIcon) {
        NewsCategory temp = blogCategoryMapper.selectByCategoryName(categoryName);
        if (temp == null) {
            NewsCategory NewsCategory = new NewsCategory();
            NewsCategory.setCategoryName(categoryName);
            NewsCategory.setCategoryIcon(categoryIcon);
            return blogCategoryMapper.insertSelective(NewsCategory) > 0;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon) {
        NewsCategory NewsCategory = blogCategoryMapper.selectByPrimaryKey(categoryId);
        if (NewsCategory != null) {
            NewsCategory.setCategoryIcon(categoryIcon);
            NewsCategory.setCategoryName(categoryName);
            //修改分类实体
            newsMapper.updateNewsCategorys(categoryName, NewsCategory.getCategoryId(), new Integer[]{categoryId});
            return blogCategoryMapper.updateByPrimaryKeySelective(NewsCategory) > 0;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean deleteBatch(Integer[] ids) {
        if (ids.length < 1) {
            return false;
        }
        //修改tb_blog表
        newsMapper.updateNewsCategorys("默认分类", 0, ids);
        //删除分类数据
        return blogCategoryMapper.deleteBatch(ids) > 0;
    }

    @Override
    public List<NewsCategory> getAllCategories() {
        return blogCategoryMapper.findCategoryList(null);
    }

}
