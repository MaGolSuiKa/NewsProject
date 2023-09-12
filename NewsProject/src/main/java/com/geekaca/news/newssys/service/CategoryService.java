package com.geekaca.news.newssys.service;



import com.geekaca.news.newssys.domain.NewsCategory;
import com.geekaca.news.newssys.utils.PageQueryUtil;
import com.geekaca.news.newssys.utils.PageResult;

import java.util.List;

public interface CategoryService {

    /**
     * 查询分类的分页数据
     *
     * @param pageUtil
     * @return
     */
    PageResult getBlogCategoryPage(PageQueryUtil pageUtil);

    int getTotalCategories();

    /**
     * 添加分类数据
     *
     * @param categoryName
     * @param categoryIcon
     * @return
     */
    Boolean saveCategory(String categoryName,String categoryIcon);

    Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon);

    Boolean deleteBatch(Integer[] ids);

    List<NewsCategory> getAllCategories();
}
