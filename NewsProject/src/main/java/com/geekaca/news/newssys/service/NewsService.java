package com.geekaca.news.newssys.service;

import com.geekaca.news.newssys.domain.News;
import com.geekaca.news.newssys.utils.PageInfo;
import com.geekaca.news.newssys.utils.PageQueryUtil;
import com.geekaca.news.newssys.utils.PageResult;
import com.geekaca.news.newssys.vo.BlogDetailVO;
import com.geekaca.news.newssys.vo.SimpleBlogListVO;


import java.util.List;

public interface NewsService {
//    String saveNews(News blog);
//
//    PageResult getNewsPage(PageQueryUtil pageUtil);
//
//    Boolean deleteBatch(Integer[] ids);
//
//    int getTotalNews();
//
//    /**
//     * 根据id获取详情
//     *
//     * @param newsId
//     * @return
//     */
//    News getNewsById(Long newsId);
//
//    /**
//     * 后台修改
//     *
//     * @param blog
//     * @return
//     */
//    String updateNews(News blog);
//
//    /**
//     * 获取首页文章列表
//     *
//     * @param page
//     * @return
//     */
//    PageResult getNewsForIndexPage(int page);
//
//    /**
//     * 首页侧边栏数据列表
//     * 0-点击最多 1-最新发布
//     *
//     * @param type
//     * @return
//     */
//    List<SimpleBlogListVO> getBlogListForIndexPage(int type);
//    /**
//     * 文章详情
//     *
//     * @param newsId
//     * @return
//     */
//    BlogDetailVO getBlogDetail(Long newsId);
//
//    /**
//     * 根据标签获取文章列表
//     *
//     * @param tagName
//     * @param page
//     * @return
//     */
//    PageResult getBlogsPageByTag(String tagName, int page);
//
//    /**
//     * 根据分类获取文章列表
//     *
//     * @param categoryId
//     * @param page
//     * @return
//     */
//    PageResult getBlogsPageByCategory(String categoryId, int page);
//
//    /**
//     * 根据搜索获取文章列表
//     *
//     * @param keyword
//     * @param page
//     * @return
//     */
//    PageResult getBlogsPageBySearch(String keyword, int page);
//
//    BlogDetailVO getBlogDetailBySubUrl(String subUrl);

    List<News> getAllNews();

    News getById(Long newsID);

    PageResult getPageNews(Integer pageNO, Integer pageSize);

}
