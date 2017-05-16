package com.llhao.gobang.ai.sort;

import com.llhao.gobang.chess.Chess;
import com.llhao.gobang.chess.Point;

import java.util.List;

/**
 * Created by Everthing-- on 2017/5/5.
 */
public interface Sorter {
    List<Point> buildPoints(Chess chess);
}
