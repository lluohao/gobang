package com.llhao.gobang.ai;

import com.llhao.gobang.chess.Chess;

/**
 * Created by llhao on 2017/4/23.
 */
public interface AI {
    ResultNode next(Chess chess, int type, int deep);
}
