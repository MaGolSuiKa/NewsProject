package com.geekaca.news.newssys.service.impl;

import com.geekaca.news.newssys.dao.*;
import com.geekaca.news.newssys.domain.News;
import com.geekaca.news.newssys.domain.NewsCategory;
import com.geekaca.news.newssys.domain.NewsTag;
import com.geekaca.news.newssys.domain.NewsTagRelation;
import com.geekaca.news.newssys.service.NewsService;
import com.geekaca.news.newssys.utils.*;
import com.geekaca.news.newssys.vo.BlogDetailVO;
import com.geekaca.news.newssys.vo.BlogListVO;
import com.geekaca.news.newssys.vo.SimpleBlogListVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class BlogServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private BlogCategoryMapper categoryMapper;
    @Autowired
    private NewsTagMapper tagMapper;
    @Autowired
    private NewsCommentMapper newsCommentMapper;
    @Autowired
    private NewsTagRelationMapper NewsTagRelationMapper;

    //新增新闻文章
    @Override
    public String saveNews(News blog) {
        //todo: 实现 新增文章的功能
        /**
         * 1, 插入文章表
         * 2， 插入 文章和标签关联表（多个标签，分隔的，要拆分成单个标签）
         */
        NewsCategory blogCategory = categoryMapper.selectByPrimaryKey(blog.getNewsCategoryId());
        if (blogCategory == null) {
            blog.setNewsCategoryId(0);
            blog.setNewsCategoryName("默认分类");
        } else {
            //设置博客分类名称
            blog.setNewsCategoryName(blogCategory.getCategoryName());
            //分类的排序值加1
            blogCategory.setCategoryRank(blogCategory.getCategoryRank() + 1);
        }
        //处理标签数据
        String[] tags = blog.getNewsTags().split(",");
        if (tags.length > 6) {
            return "标签数量限制为6";
        }
        //保存文章
        if (newsMapper.insertSelective(blog) > 0) {
            //新增的tag对象
            List<NewsTag> tagListForInsert = new ArrayList<>();
            //所有的tag对象，用于建立关系数据
            List<NewsTag> allTagsList = new ArrayList<>();
            for (int i = 0; i < tags.length; i++) {
                NewsTag tag = tagMapper.selectByTagName(tags[i]);
                if (tag == null) {
                    //不存在就新增
                    NewsTag tempTag = new NewsTag();
                    tempTag.setTagName(tags[i]);
                    tagListForInsert.add(tempTag);
                } else {
                    allTagsList.add(tag);
                }
            }
            //新增标签数据并修改分类排序值
            if (!CollectionUtils.isEmpty(tagListForInsert)) {
                tagMapper.batchInsertBlogTag(tagListForInsert);
            }
            if (blogCategory != null) {
                categoryMapper.updateByPrimaryKeySelective(blogCategory);
            }
            List<NewsTagRelation> blogTagRelations = new ArrayList<>();
            //新增关系数据
            allTagsList.addAll(tagListForInsert);
            for (NewsTag tag : allTagsList) {
                NewsTagRelation blogTagRelation = new NewsTagRelation();
                blogTagRelation.setBlogId(blog.getNewsId());
                blogTagRelation.setTagId(tag.getTagId());
                blogTagRelations.add(blogTagRelation);
            }
            if (NewsTagRelationMapper.batchInsert(blogTagRelations) > 0) {
                return "success";
            }
        }
        return "保存失败";
    }

    @Override
    public PageResult getNewsPage(PageQueryUtil pageUtil) {
        //根据分页参数查询文章列表  查询这一页的数据
        List<News> newsList = newsMapper.findNewsList(pageUtil);
        //查询总记录条数
        int total = newsMapper.getTotalNewss(pageUtil);
        PageResult pageResult = new PageResult(newsList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return newsMapper.deleteBatch(ids) > 0;
    }

    @Override
    public int getTotalNews() {
        return newsMapper.getTotalNewss(null);
    }

    @Override
    public News getNewsById(Long newsId) {
        return newsMapper.selectByPrimaryKey(newsId);
    }

    @Override
    public String updateNews(News news) {
        News newForUpdate = newsMapper.selectByPrimaryKey(news.getNewsId());
        if (newForUpdate == null) {
            return "数据不存在";
        }
        newForUpdate.setNewsTitle(news.getNewsTitle());
        newForUpdate.setNewsSubUrl(news.getNewsSubUrl());
        newForUpdate.setNewsContent(news.getNewsContent());
        newForUpdate.setNewsCoverImage(news.getNewsCoverImage());
        newForUpdate.setNewsStatus(news.getNewsStatus());
        newForUpdate.setEnableComment(news.getEnableComment());
        NewsCategory blogCategory = categoryMapper.selectByPrimaryKey(news.getNewsCategoryId());
        if (blogCategory == null) {
            newForUpdate.setNewsCategoryId(0);
            newForUpdate.setNewsCategoryName("默认分类");
        } else {
            //设置博客分类名称
            newForUpdate.setNewsCategoryName(blogCategory.getCategoryName());
            newForUpdate.setNewsCategoryId(blogCategory.getCategoryId());
            //分类的排序值加1
            blogCategory.setCategoryRank(blogCategory.getCategoryRank() + 1);
        }
        //处理标签数据
        String[] tags = news.getNewsTags().split(",");
        if (tags.length > 6) {
            return "标签数量限制为6";
        }
        newForUpdate.setNewsTags(news.getNewsTags());
        //新增的tag对象
        List<NewsTag> tagListForInsert = new ArrayList<>();
        //所有的tag对象，用于建立关系数据
        List<NewsTag> allTagsList = new ArrayList<>();
        for (int i = 0; i < tags.length; i++) {
            NewsTag tag = tagMapper.selectByTagName(tags[i]);
            if (tag == null) {
                //不存在就新增
                NewsTag tempTag = new NewsTag();
                tempTag.setTagName(tags[i]);
                tagListForInsert.add(tempTag);
            } else {
                allTagsList.add(tag);
            }
        }
        //新增标签数据不为空->新增标签数据
        if (!CollectionUtils.isEmpty(tagListForInsert)) {
            tagMapper.batchInsertBlogTag(tagListForInsert);
        }
        List<NewsTagRelation> blogTagRelations = new ArrayList<>();
        //新增关系数据
        allTagsList.addAll(tagListForInsert);
        for (NewsTag tag : allTagsList) {
            NewsTagRelation blogTagRelation = new NewsTagRelation();
            blogTagRelation.setBlogId(news.getNewsId());
            blogTagRelation.setTagId(tag.getTagId());
            blogTagRelations.add(blogTagRelation);
        }
        //修改blog信息->修改分类排序值->删除原关系数据->保存新的关系数据
        if (blogCategory != null) {
            categoryMapper.updateByPrimaryKeySelective(blogCategory);
        }
        NewsTagRelationMapper.deleteByBlogId(news.getNewsId());
        NewsTagRelationMapper.batchInsert(blogTagRelations);
        if (newsMapper.updateByPrimaryKeySelective(newForUpdate) > 0) {
            return "success";
        }
        return "修改失败";

    }

    @Override
    public PageResult getNewsForIndexPage(int page) {
        Map params = new HashMap();
        params.put("page", page);
        //每页8条
        params.put("limit", 8);
        params.put("blogStatus", 1);//过滤发布状态下的数据
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        List<News> blogList = newsMapper.findNewsList(pageUtil);
        List<BlogListVO> blogListVOS = getBlogListVOsByBlogs(blogList);
        int total = newsMapper.getTotalNewss(pageUtil);
        PageResult pageResult = new PageResult(blogListVOS, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    private List<BlogListVO> getBlogListVOsByBlogs(List<News> newsList) {
        List<BlogListVO> blogListVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(newsList)) {
            List<Integer> categoryIds = newsList.stream().map(News::getNewsCategoryId).collect(Collectors.toList());
            Map<Integer, String> blogCategoryMap = new HashMap<>();
            if (!CollectionUtils.isEmpty(categoryIds)) {
                List<NewsCategory> blogCategories = categoryMapper.selectByCategoryIds(categoryIds);
                if (!CollectionUtils.isEmpty(blogCategories)) {
                    blogCategoryMap = blogCategories.stream().collect(Collectors.toMap(NewsCategory::getCategoryId, NewsCategory::getCategoryIcon, (key1, key2) -> key2));
                }
            }
            for (News news : newsList) {
                BlogListVO blogListVO = new BlogListVO();
                BeanUtils.copyProperties(news, blogListVO);
                System.out.println(news);
                System.out.println(blogListVO);
                if (blogCategoryMap.containsKey(news.getNewsCategoryId())) {
                    blogListVO.setNewsCategoryIcon(blogCategoryMap.get(news.getNewsCategoryId()));
                } else {
                    blogListVO.setNewsCategoryId(0);
                    blogListVO.setNewsCategoryName("默认分类");
                    blogListVO.setNewsCategoryIcon("/admin/dist/img/category/00.png");
                }
                blogListVOS.add(blogListVO);
            }
        }
        return blogListVOS;
    }

    @Override
    public List<SimpleBlogListVO> getBlogListForIndexPage(int type) {
        List<SimpleBlogListVO> simpleBlogListVOS = new ArrayList<>();
        List<News> news = newsMapper.findNewsListByType(type, 9);
        if (!CollectionUtils.isEmpty(news)) {
            for (News news1 : news) {
                SimpleBlogListVO simpleBlogListVO = new SimpleBlogListVO();
                BeanUtils.copyProperties(news1, simpleBlogListVO);
                simpleBlogListVOS.add(simpleBlogListVO);
            }
        }
        return simpleBlogListVOS;
    }

    @Override
    public BlogDetailVO getBlogDetail(Long newsId) {
        News news = newsMapper.selectByPrimaryKey(newsId);
        //不为空且状态为已发布
        BlogDetailVO blogDetailVO = getBlogDetailVO(news);
        if (blogDetailVO != null) {
            return blogDetailVO;
        }
        return null;
    }

    /**
     * 方法抽取
     *
     * @param news
     * @return
     */
    private BlogDetailVO getBlogDetailVO(News news) {
        if (news != null && news.getNewsStatus() == 1) {
            //增加浏览量
            news.setNewsViews(news.getNewsViews() + 1);
            newsMapper.updateByPrimaryKey(news);
            BlogDetailVO blogDetailVO = new BlogDetailVO();
            BeanUtils.copyProperties(news, blogDetailVO);
            System.out.println(news);
            System.out.println(blogDetailVO);
            blogDetailVO.setNewsContent(MarkDownUtil.mdToHtml(blogDetailVO.getNewsContent()));
            NewsCategory newsCategory = categoryMapper.selectByPrimaryKey(news.getNewsCategoryId());
            if (newsCategory == null) {
                newsCategory = new NewsCategory();
                newsCategory.setCategoryId(0);
                newsCategory.setCategoryName("默认分类");
                newsCategory.setCategoryIcon("/admin/dist/img/category/00.png");
            }
            //分类信息
            blogDetailVO.setNewsCategoryIcon(newsCategory.getCategoryIcon());
            if (StringUtils.hasText(news.getNewsTags())) {
                //标签设置
                List<String> tags = Arrays.asList(news.getNewsTags().split(","));
                blogDetailVO.setNewsTags(tags);
            }
            //设置评论数
            Map params = new HashMap();
            params.put("blogId", news.getNewsId());
            params.put("commentStatus", 1);//过滤审核通过的数据
            blogDetailVO.setCommentCount(newsCommentMapper.getTotalNewsComments(params));
            return blogDetailVO;
        }
        return null;
    }

    @Override
    public PageResult getBlogsPageByTag(String tagName, int page) {
        if (PatternUtil.validKeyword(tagName)) {
            NewsTag tag = tagMapper.selectByTagName(tagName);
            if (tag != null && page > 0) {
                Map param = new HashMap();
                param.put("page", page);
                param.put("limit", 9);
                param.put("tagId", tag.getTagId());
                PageInfo pageUtil = new PageInfo(param);
                List<News> newsList = newsMapper.getNewssPageByTagId(pageUtil);
                List<BlogListVO> blogListVOS = getBlogListVOsByBlogs(newsList);
                int total = newsMapper.getTotalNewssByTagId(pageUtil);
                PageResult pageResult = new PageResult(blogListVOS, total, pageUtil.getLimit(), pageUtil.getPage());
                return pageResult;
            }
        }
        return null;
    }


    @Override
    public PageResult getBlogsPageByCategory(String categoryName, int page) {
        if (PatternUtil.validKeyword(categoryName)) {
            NewsCategory newsCategory = categoryMapper.selectByCategoryName(categoryName);
            if ("默认分类".equals(categoryName) && newsCategory == null) {
                newsCategory = new NewsCategory();
                newsCategory.setCategoryId(0);
            }
            if (newsCategory != null && page > 0) {
                Map param = new HashMap();
                param.put("page", page);
                param.put("limit", 9);
                param.put("newsCategoryId", newsCategory.getCategoryId());
                param.put("newsStatus", 1);//过滤发布状态下的数据
                PageQueryUtil pageUtil = new PageQueryUtil(param);
                List<News> blogList = newsMapper.findNewsList(pageUtil);
                List<BlogListVO> blogListVOS = getBlogListVOsByBlogs(blogList);
                int total = newsMapper.getTotalNewss(pageUtil);
                PageResult pageResult = new PageResult(blogListVOS, total, pageUtil.getLimit(), pageUtil.getPage());
                return pageResult;
            }
        }
        return null;
    }

    @Override
    public PageResult getBlogsPageBySearch(String keyword, int page) {
        if (page > 0 && PatternUtil.validKeyword(keyword)) {
            Map param = new HashMap();
            param.put("page", page);
            param.put("limit", 9);
            param.put("keyword", keyword);
            param.put("blogStatus", 1);//过滤发布状态下的数据
            PageQueryUtil pageUtil = new PageQueryUtil(param);
            List<News> newsList = newsMapper.findNewsList(pageUtil);
            List<BlogListVO> blogListVOS = getBlogListVOsByBlogs(newsList);
            int total = newsMapper.getTotalNewss(pageUtil);
            PageResult pageResult = new PageResult(blogListVOS, total, pageUtil.getLimit(), pageUtil.getPage());
            return pageResult;
        }
        return null;
    }

    @Override
    public BlogDetailVO getBlogDetailBySubUrl(String subUrl) {
        News news = newsMapper.selectBySubUrl(subUrl);
        //不为空且状态为已发布
        BlogDetailVO blogDetailVO = getBlogDetailVO(news);
        if (blogDetailVO != null) {
            return blogDetailVO;
        }
        return null;
    }


}
