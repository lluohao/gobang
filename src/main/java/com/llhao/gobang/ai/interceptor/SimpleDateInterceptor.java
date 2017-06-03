package com.llhao.gobang.ai.interceptor;

import com.llhao.gobang.ai.ResultNode;
import com.llhao.gobang.chess.Chess;

/**
 * Created by llhao on 2017/4/24.
 */
public class SimpleDateInterceptor implements Interceptor {
    @Override
    public boolean intercept(ResultNode node, int i, int j) {
        Chess chess = node.getChess();
        return chess.get(i, j) == 0;
    }
}
