package com.llhao.gobang.web.controller;

import com.llhao.gobang.service.IAiService;
import com.llhao.gobang.web.vo.AIResultView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Everthing-- on 2017/5/16.
 */
@Controller
public class AIController {
    @Autowired
    private IAiService service;
    @RequestMapping("/next")
    public @ResponseBody AIResultView next(int type,int deep){
        return service.next(null,type,deep);
    }
}
