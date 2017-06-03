package com.llhao.gobang.service.impl;

import com.llhao.gobang.chess.ChessNode;
import com.llhao.gobang.chess.DynamicChess;
import com.llhao.gobang.entity.User;
import com.llhao.gobang.service.IGameService;
import com.llhao.gobang.service.IRecordService;
import com.llhao.gobang.service.po.Game;
import com.llhao.gobang.web.vo.ChessResultView;
import com.llhao.gobang.web.vo.GameStatusView;
import com.llhao.gobang.web.vo.PlayChessView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    public static List<User> pools = new Vector<>();
    public static Map<User,Game> games = new ConcurrentHashMap<>();
    private Thread thread = new Thread();
    @Autowired
    private IRecordService recordService;
    public GameServiceImpl(){
        Runnable run = new Runnable() {
            @Override
            public void run() {
                while(true){
                    synchronized (pools) {
                        while (pools.size() >= 2) {
                            System.out.println(pools.size());
                            User u1 = pools.remove(0);
                            User u2 = pools.remove(0);
                            Game game = new Game();
                            game.setNow(1);
                            game.setBlack(u1);
                            game.setWhite(u2);
                            game.setTime(new Timestamp(System.currentTimeMillis()));
                            game.setChess(new DynamicChess());
                            games.put(u1, game);
                            games.put(u2, game);
                        }
                    }
                    handleGames();
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){
                    }

                }
            }

            public void handleGames(){
                Set<User> users = games.keySet();
                for (User user : users) {
                    Game game = games.get(user);
                    if(game.getWin()!=0){

                    }
                }
                //取出所有Game,判断是否结束
                //如果结束，保存游戏，将用户状态重置为仅登录
                //再判断是否长时间未连接，如果是，则认定最后一个连接的人胜
            }
        };
        thread = new Thread(run);
        thread.start();
    }
    @Override
    public ChessResultView step(Game game, int index) {
        ChessResultView view = new ChessResultView();
        if(game==null){
            view.setCode(500);
            view.setMessage("没有在游戏中");
            return view;
        }
        if(game.isSave()){
            view.setCode(415);
            view.setMessage("游戏已经结束");
            return view;
        }
        try {
            ChessNode result = game.getStep(index);
            view.setCode(200);
            view.setX(result.getX());
            view.setY(result.getY());
        }catch (Exception e){
            view.setCode(500);
            view.setMessage(e.getMessage());
        }
        if(game.getWin()!=0){
            view.setMessage(game.getWin()==1?"黑方胜":"白方胜");
            view.setWin(game.getWin());
            synchronized (game){
                games.remove(game.getBlack());
                games.remove(game.getWhite());
                try {
                    recordService.save(game);
                }catch (Exception e){
                    return view;
                }
            }
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
            return gsv;
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
            Game game = games.get(user);
            view.setStatus(STATUS_GAME);
            view.setCode(200);
            view.setType(game.getBlack()==user?1:-1);
            view.setMessage("you are playing now!");
            return view;
        }
        view.setCode(200);
        view.setStatus(STATUS_NONE);
        view.setMessage("just login in");
        return view;
    }

    public IRecordService getRecordService() {
        return recordService;
    }

    public void setRecordService(IRecordService recordService) {
        this.recordService = recordService;
    }
}
