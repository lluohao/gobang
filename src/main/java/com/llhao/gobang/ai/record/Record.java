package com.llhao.gobang.ai.record;

import com.llhao.gobang.ai.ResultNode;
import com.llhao.gobang.chess.Chess;

/**
 * Created by Everthing-- on 2017/5/5.
 */
public interface Record {
    void addNode(Chess chess, int score);
    Integer find(ResultNode node);
}
