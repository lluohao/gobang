package com.llhao.gobang.service;

import com.llhao.gobang.service.po.Game;
import com.llhao.gobang.web.vo.ChessResultView;

/**
 * Created by luohao-- on 2017/5/19.
 */
public interface IGameService {
    ChessResultView step(Game game, int index);
}
