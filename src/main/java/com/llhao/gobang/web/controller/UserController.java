package com.llhao.gobang.web.controller;

import com.llhao.gobang.entity.User;
import com.llhao.gobang.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by HAO zong on 2017-4-13.
 */
@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/login")
    public String login(@RequestParam String name, @RequestParam String password, Model model, HttpSession session) {
        model.addAttribute("message", "登录失败！");
        try {
            User user = userService.login(name, password);
            if (user == null) {
                model.addAttribute("message", "用户名或者密码错误");
                return "login";
            } else {
                session.setAttribute("user", user);
                model.addAttribute("message", "登录成功，正在跳转......");
                model.addAttribute("url", "index.jsp");
                return "jump";
            }
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "login";
        }
    }

    @RequestMapping("/register")
    public String register(@RequestParam String name, @RequestParam String password, @RequestParam String email, Model model) {
        try {
            userService.addUser(name, password, email);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "register";
        }
        model.addAttribute("message", "注册成功，正在跳转");
        model.addAttribute("url", "login.jsp");
        return "jump";
    }
}
