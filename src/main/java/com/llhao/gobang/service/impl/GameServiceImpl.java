package com.llhao.gobang.service.impl;

import com.llhao.gobang.chess.ChessNode;
import com.llhao.gobang.entity.User;
import com.llhao.gobang.service.IGameService;
import com.llhao.gobang.service.po.Game;
import com.llhao.gobang.web.vo.ChessResultView;
import com.llhao.gobang.web.vo.GameStatusView;
import com.llhao.gobang.web.vo.PlayChessView;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by luohao on 2017/5/19.
 */
@Service
public class GameServiceImpl implements IGameService {
    public static final int STATUS_POOL = 1;
    public static final int STATUS_GAME = 2;
    public static final int STATUS_NONE = 3;
    private static List<User> pools = new Vector<>();
    private static Map<User,Game> games = new ConcurrentHashMap<>();
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

    @Override
    public PlayChessView play(int x, int y, int type, User user, Game game) {
        if(game.getNow()!=type){
            throw new RuntimeException("没有到你下棋");
        }else if(game.getUser(type)!=user){
            throw new RuntimeException("请为自己行棋");
        }else {
            int value = game.getChess().get(x, y);
            if(value!=0){
                throw new RuntimeException("已经有棋子了");
            }
            game.play(x, y, type);
        }
        PlayChessView view = new PlayChessView();
        view.setCode(200);
        view.setMessage("SUCCESS");
        return view;
    }

    @Override
    public GameStatusView addToGamePool(User user) {
        GameStatusView gsv = new GameStatusView();
        if(user==null){
            gsv.setCode(404);
            gsv.setMessage("please login first");
        }
        pools.add(user);
        gsv.setCode(200);
        gsv.setMessage("add to pool");
        gsv.setStatus(STATUS_POOL);
        return gsv;
    }

    @Override
    public GameStatusView removeFromGamePool(User user) {
        return null;
    }

    @Override
    public GameStatusView gameStatus(User user) {
        GameStatusView view = new GameStatusView();
        if(pools.contains(user)){
            view.setStatus(STATUS_POOL);
            view.setCode(200);
            view.setMessage("you now in pool");
            return view;
        }
        if(games.get(user)!=null){
            view.setStatus(STATUS_GAME);
            view.setCode(200);
            view.setMessage("you are playing now!");
        }
        view.setCode(200);
        view.setStatus(STATUS_NONE);
        view.setMessage("just login in");
        return view;
    }
}
