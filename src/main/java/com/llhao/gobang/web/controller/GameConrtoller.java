package com.llhao.gobang.web.controller;

import com.llhao.gobang.ai.eval.DynamicEvaluation;
import com.llhao.gobang.chess.DynamicChess;
import com.llhao.gobang.entity.User;
import com.llhao.gobang.service.IGameService;
import com.llhao.gobang.service.po.Game;
import com.llhao.gobang.web.vo.ChessResultView;
import com.llhao.gobang.web.vo.NewGameView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

/**
 * Created by luohao on 2017/5/19.
 */
@Controller
public class GameConrtoller {
    @Autowired
    private IGameService gameService;

    @RequestMapping("/step")
    public @ResponseBody ChessResultView step(@RequestParam int step, HttpSession session){
        Game game = (Game) session.getAttribute("game");
        return gameService.step(game,step);
    }

    @RequestMapping("/newGame")
    public @ResponseBody NewGameView newGame(@RequestParam int type, HttpSession session, ServletContext context){
        return null;
    }

    public @ResponseBody NewGameView newComputer(String str, int type, HttpSession session){
        Game game = new Game();
        DynamicEvaluation evaluation = new DynamicEvaluation();
        game.setChess(DynamicChess.fromDate(str,evaluation));
        game.setTime(new Timestamp(System.currentTimeMillis()));
        game.setBlack((User) session.getAttribute("user"));


    }
}
