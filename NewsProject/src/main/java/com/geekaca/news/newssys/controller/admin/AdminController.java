package com.geekaca.news.newssys.controller.admin;

import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.crypto.SecureUtil;
import com.geekaca.news.newssys.domain.AdminUser;
import com.geekaca.news.newssys.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminUserService userService;
    //接收登陆

    @GetMapping("/login")
    public String login(){
        //指向页面
        return "/admin/login";
    }

    @RequestMapping("/dologin")
    public String doLogin(@RequestParam("userName") String userName,
                          @RequestParam("password") String password,
                          @RequestParam("verifyCode") String verifyCode,
                          HttpSession session) {
        if (!StringUtils.hasText(verifyCode)) {
            session.setAttribute("errorMsg", "验证码不能为空");
            return "admin/login";
        }
        if (!StringUtils.hasText(userName) || !StringUtils.hasText(password)){
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "admin/login";
        }
        //判断用户输入的验证码 和之前生成的验证码是否一致 （用户打开登陆页面，访问了验证码控制器，把验证码存入了session）
        //会话：每个用户各自独立的，相互隔离的
        ShearCaptcha shearCaptcha = (ShearCaptcha) session.getAttribute("verifyCode");
        if (shearCaptcha == null || !shearCaptcha.verify(verifyCode)) {
            session.setAttribute("errorMsg", "验证码错误");
            return "admin/login";
        }
        //连接DB 验证用户名和密码
        AdminUser user = userService.login(userName, SecureUtil.md5(password));
        if (user == null){
            session.setAttribute("errorMsg", "登陆失败");
        }else{
            session.setAttribute("loginUser", user.getNickName());
            session.setAttribute("loginUserId", user.getAdminUserId());
            //session过期时间设置为7200秒 即两小时
            //session.setMaxInactiveInterval(60 * 60 * 2);
            return "redirect:/admin/index";
        }
        return null;
    }

    @GetMapping({"", "/", "/index", "/index.html"})
    public String index(HttpServletRequest request) {
        request.setAttribute("path", "index");
        //类别数量  ctrl  + Alt 鼠标左键点击，跳转到函数实现
        request.setAttribute("categoryCount", 0);
        request.setAttribute("blogCount", 0);
        request.setAttribute("linkCount", 0);
        request.setAttribute("tagCount", 0);
        request.setAttribute("commentCount", 0);
        return "admin/index";
    }
}
