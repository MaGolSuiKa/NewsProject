package com.geekaca.news.newssys.controller.news;

import cn.hutool.captcha.ShearCaptcha;
import com.geekaca.news.newssys.domain.News;
import com.geekaca.news.newssys.domain.NewsComment;
import com.geekaca.news.newssys.service.CommentService;
import com.geekaca.news.newssys.service.ConfigService;
import com.geekaca.news.newssys.service.NewsService;
import com.geekaca.news.newssys.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * 前台
 */
@Controller
@Slf4j

public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private ConfigService configService;
    @Autowired
    private CommentService commentService;

    public static String theme = "amaze";

    /**
     * 首页
     *
     * @return
     */
    @GetMapping({"/", "/index", "index.html"})
    public String index(HttpServletRequest request) {
        return this.page(request, 1);
    }

    @GetMapping({"/page/{pageNum}"})
    private String page(HttpServletRequest request, @PathVariable("pageNum") int pageNum) {
        PageResult pageNews = newsService.getPageNews(pageNum, 5,null);

        request.setAttribute("blogPageResult", pageNews);
        //新发布
        request.setAttribute("newBlogs", 0);
        //点击最多
        request.setAttribute("hotBlogs", 0);
        //热门标签
        request.setAttribute("hotTags", 0);

        request.setAttribute("pageName", "首页");
        request.setAttribute("configurations", configService.getAllConfigs());
        return "blog/" + theme + "/index";
    }

    //传递集合
    @RequestMapping("/all")
    public String getAll(HttpServletRequest req){
        List<News> allNews = newsService.getAllNews();
        req.setAttribute("all", allNews);
        return "newsList";

    }
    //单个值的传递
    @GetMapping("/toPage")
    public String toPage(HttpServletRequest req){
        req.setAttribute("name", "Tom");
        //要用@Controller 跳转到名字为hello的页面
        return "hello";
    }
    //从控制器向页面传递对象
    @GetMapping("/newsDetail/{newsID}")
    public String toDetail(HttpServletRequest request, @PathVariable("newsID") Long newsID){
        News news = newsService.getById(newsID);
        request.setAttribute("newsInfo", news);
        return "newsDetail";
    }

    //详情页
    @GetMapping({"/blog/{newsId}", "/article/{newsId}"})
    public String detail(HttpServletRequest request, @PathVariable("newsId") Long newsId, @RequestParam(value = "commentPage", required = false, defaultValue = "1") Integer commentPage) {

        News newsWithComments = newsService.getById(newsId);
        request.setAttribute("blogDetailVO", newsWithComments);
        request.setAttribute("pageName", "详情");
        request.setAttribute("configurations", configService.getAllConfigs());
        int i = newsService.updateNewsViews(newsId);
        return "blog/" + theme + "/detail";
    }
    //评论操作
    @PostMapping(value = "/blog/comment")
    @ResponseBody
    public Result comment(HttpServletRequest request, HttpSession session,
                          @RequestParam Long newsId, @RequestParam String verifyCode,
                          @RequestParam String commentator, @RequestParam String email,
                          @RequestParam String websiteUrl, @RequestParam String commentBody) {
        if (!StringUtils.hasText(verifyCode)) {
            return ResultGenerator.genFailResult("验证码不能为空");
        }
        ShearCaptcha shearCaptcha = (ShearCaptcha) session.getAttribute("verifyCode");
        if (shearCaptcha == null || !shearCaptcha.verify(verifyCode)) {
            return ResultGenerator.genFailResult("验证码错误");
        }
        String ref = request.getHeader("Referer");
        if (!StringUtils.hasText(ref)) {
            return ResultGenerator.genFailResult("非法请求");
        }
        if (null == newsId || newsId < 0) {
            return ResultGenerator.genFailResult("非法请求");
        }
        if (!StringUtils.hasText(commentator)) {
            return ResultGenerator.genFailResult("请输入称呼");
        }
        if (!StringUtils.hasText(email)) {
            return ResultGenerator.genFailResult("请输入邮箱地址");
        }
        if (!PatternUtil.isEmail(email)) {
            return ResultGenerator.genFailResult("请输入正确的邮箱地址");
        }
        if (!StringUtils.hasText(commentBody)) {
            return ResultGenerator.genFailResult("请输入评论内容");
        }
        if (commentBody.trim().length() > 200) {
            return ResultGenerator.genFailResult("评论内容过长");
        }
        NewsComment comment = new NewsComment();
        comment.setNewsId(newsId);
        comment.setCommentator(MyBlogUtils.cleanString(commentator));
        comment.setEmail(email);
        if (PatternUtil.isURL(websiteUrl)) {
            comment.setWebsiteUrl(websiteUrl);
        }
        comment.setCommentBody(MyBlogUtils.cleanString(commentBody));
        return ResultGenerator.genSuccessResult(commentService.addComment(comment));
    }
}
