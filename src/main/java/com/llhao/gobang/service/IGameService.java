package com.llhao.gobang.service;

import com.llhao.gobang.entity.User;
import com.llhao.gobang.service.po.Game;
import com.llhao.gobang.web.vo.ChessResultView;
import com.llhao.gobang.web.vo.GameStatusView;
import com.llhao.gobang.web.vo.PlayChessView;

/**
 * Created by luohao on 2017/5/19.
 */
public interface IGameService {
    ChessResultView step(Game game, int index);
    PlayChessView play(int x, int y, int type, User user, Game game);
    GameStatusView addToGamePool(User user);
    GameStatusView removeFromGamePool(User user);
    GameStatusView gameStatus(User user);

}
