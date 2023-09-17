package com.geekaca.news.newssys.controller.common;



import com.geekaca.news.newssys.service.CommentService;
import com.geekaca.news.newssys.utils.PageBean;
import com.geekaca.news.newssys.utils.Result;
import com.geekaca.news.newssys.utils.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class CommentController {

    @Resource
    private CommentService commentService;

    @GetMapping("/comments/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        Object page = params.get("page");
        Object limit = params.get("limit");
        if (ObjectUtils.isEmpty(params.get("page")) || ObjectUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }

        PageBean pageBean = new PageBean();
        int intPageNo = Integer.parseInt(page.toString());
        int intLimit = Integer.parseInt(limit.toString());
        int start = (intPageNo - 1) * intLimit;
        pageBean.setPageNo(start);
        pageBean.setPageSize(intLimit);

        return ResultGenerator.genSuccessResult(commentService.getCommentsPage(pageBean));
    }

    /**
     * 审核 评论
     * @param ids  多条评论的id
     * @return
     */
    @PostMapping("/comments/checkDone")
    @ResponseBody
    public Result checkDone(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (commentService.checkDone(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("审核失败");
        }
    }
   @PostMapping("/comments/delete")
   @ResponseBody
   public Result delete(@RequestBody Integer[] ids) {
       if (ids.length < 1) {
           return ResultGenerator.genFailResult("参数异常！");
       }
       if (commentService.deleteBatch(ids)) {
           return ResultGenerator.genSuccessResult();
       } else {
           return ResultGenerator.genFailResult("刪除失败");
       }
   }

    @GetMapping("/comments")
    public String list(HttpServletRequest request) {
        request.setAttribute("path", "comments");
        return "admin/comment";
    }


}
