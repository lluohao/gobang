package com.llhao.gobang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.llhao.gobang.entity.User;
import com.llhao.gobang.service.IUserService;

/**
 * Created by llhao on 2017/3/10.
 */
@Controller
public class UserController {
	private IUserService userService;
    @RequestMapping("/login")
    public ModelAndView login(String name, String password){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        mv.addObject("message","你好！");
        return mv;
    }
    @RequestMapping("/register")
    public String register(User user,Model model){
        return "register";
    }
}
