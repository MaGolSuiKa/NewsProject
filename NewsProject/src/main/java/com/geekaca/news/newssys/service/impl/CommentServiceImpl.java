package com.geekaca.news.newssys.service.impl;


import com.geekaca.news.newssys.domain.NewsComment;
import com.geekaca.news.newssys.service.CommentService;
import com.geekaca.news.newssys.utils.PageInfo;
import com.geekaca.news.newssys.utils.PageQueryUtil;
import com.geekaca.news.newssys.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private com.geekaca.news.newssys.dao.NewsCommentMapper newsCommentMapper;

    @Override
    public Boolean addComment(NewsComment NewsComment) {
        return newsCommentMapper.insertSelective(NewsComment) > 0;
    }

    @Override
    public PageResult getCommentsPage(PageQueryUtil pageUtil) {
        List<NewsComment> comments = newsCommentMapper.findNewsCommentList(pageUtil);
        int total = newsCommentMapper.getTotalNewsComments(pageUtil);
        PageResult pageResult = new PageResult(comments, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public int getTotalComments() {
        return newsCommentMapper.getTotalNewsComments(null);
    }

    @Override
    public Boolean checkDone(Integer[] ids) {
        return newsCommentMapper.checkDone(ids) > 0;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return newsCommentMapper.deleteBatch(ids) > 0;
    }

    @Override
    public Boolean reply(Long commentId, String replyBody) {
        NewsComment NewsComment = newsCommentMapper.selectByPrimaryKey(commentId);
        //NewsComment不为空且状态为已审核，则继续后续操作
        if (NewsComment != null && NewsComment.getCommentStatus().intValue() == 1) {
            NewsComment.setReplyBody(replyBody);
            NewsComment.setReplyCreateTime(new Date());
            return newsCommentMapper.updateByPrimaryKeySelective(NewsComment) > 0;
        }
        return false;
    }

    @Override
    public PageResult getCommentPageByBlogIdAndPageNum(Long NewsId, int page) {
        if (page < 1) {
            return null;
        }
        Map params = new HashMap();
        params.put("page", page);
        //每页8条
        params.put("limit", 8);
        params.put("newsId", NewsId);
        params.put("commentStatus", 1);//过滤审核通过的数据
        PageInfo pageUtil = new PageInfo(params);
        List<NewsComment> comments = newsCommentMapper.findNewsCommentList(pageUtil);
        if (!CollectionUtils.isEmpty(comments)) {
            int total = newsCommentMapper.getTotalNewsComments(pageUtil);
            PageResult pageResult = new PageResult(comments, total, pageUtil.getLimit(), pageUtil.getPage());
            return pageResult;
        }
        return null;
    }
}
