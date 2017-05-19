package com.llhao.gobang.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by luohao-- on 2017/5/17.
 */
@Controller
public class JspController {
    @RequestMapping("/{path}jsp")
    public String request(@PathVariable String path){
        System.out.println(path);
        return path;
    }
}
