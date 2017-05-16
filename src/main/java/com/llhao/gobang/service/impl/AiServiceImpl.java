package com.llhao.gobang.service.impl;

import com.llhao.gobang.ai.AI;
import com.llhao.gobang.ai.MinmaxAI;
import com.llhao.gobang.ai.ResultNode;
import com.llhao.gobang.chess.DynamicChess;
import com.llhao.gobang.service.IAiService;
import com.llhao.gobang.web.vo.AIResultView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Everthing-- on 2017/5/16.
 */
@Service
public class AiServiceImpl implements IAiService {
    @Autowired
    private MinmaxAI minMaxAi;
    @Override
    public AIResultView next(String data, int type, int deep) {
        DynamicChess chess = DynamicChess.fromDate(data,minMaxAi.getEvaluation());
        ResultNode resultNode = minMaxAi.next(chess,type,deep);
        AIResultView view = new AIResultView();
        view.setX(resultNode.getX());
        view.setY(resultNode.getY());
        view.setCode(200);
        view.setScore(resultNode.getScore());
        return view;
    }
}
