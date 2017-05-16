package com.llhao.gobang.ai.eval;

import com.llhao.gobang.chess.Chess;

/**
 * Created by llhao on 2017/4/22.
 */
public interface Evaluation {
    int eval(Chess chess, int type);

    int absEval(Chess chess, int type);

    int eval(Chess chess, int type, int next);

    int win(Chess chess);
}
