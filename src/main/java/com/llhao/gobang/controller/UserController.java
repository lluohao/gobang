package com.llhao.gobang.controller;

import com.llhao.gobang.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by llhao on 2017/3/10.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @RequestMapping("/login")
    public ModelAndView login(@RequestParam String name, @RequestParam String password){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/login.jsp");
        mv.addObject("message","你好！");
        return mv;
    }
}
