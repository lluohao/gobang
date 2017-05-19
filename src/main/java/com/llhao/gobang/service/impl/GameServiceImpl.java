package com.llhao.gobang.service.impl;

import com.llhao.gobang.chess.ChessNode;
import com.llhao.gobang.service.IGameService;
import com.llhao.gobang.service.po.Game;
import com.llhao.gobang.web.vo.ChessResultView;
import org.springframework.stereotype.Service;

/**
 * Created by luohao on 2017/5/19.
 */
@Service
public class GameServiceImpl implements IGameService {
    @Override
    public ChessResultView step(Game game, int index) {
        ChessResultView view = new ChessResultView();
        if(game==null){
            view.setCode(500);
            view.setMessage("没有在游戏中");
        }
        try {
            ChessNode result = game.getStep(index);
            view.setCode(200);
            view.setX(result.getX());
            view.setY(result.getY());
            if(game.getWin()!=0){
                view.setMessage(game.getWin()==1?"黑方胜":"白方胜");
                view.setWin(game.getWin());
            }
        }catch (Exception e){
            view.setCode(500);
            view.setMessage(e.getMessage());
        }
        return view;
    }
}
