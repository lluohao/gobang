package com.llhao.gobang.ai.record;

import com.llhao.gobang.ai.ResultNode;
import com.llhao.gobang.chess.Chess;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Everthing-- on 2017/5/5.
 */
public class MapRecord implements Record {
    private Map<Integer,Integer> scoreMap = new HashMap<>();

    @Override
    public Integer find(ResultNode node) {
       return null;
    }

    @Override
    public void addNode(Chess chess, int score) {

    }
}
