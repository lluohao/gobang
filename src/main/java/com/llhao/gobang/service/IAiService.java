package com.llhao.gobang.service;

import com.llhao.gobang.web.vo.AIResultView;

/**
 * Created by Everthing-- on 2017/5/16.
 */
public interface IAiService {
    AIResultView next(String data,int type,int deep);
}
