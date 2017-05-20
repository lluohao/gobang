package com.llhao.gobang.web.controller;

import com.llhao.gobang.ai.eval.DynamicEvaluation;
import com.llhao.gobang.chess.ChessNode;
import com.llhao.gobang.chess.DynamicChess;
import com.llhao.gobang.entity.User;
import com.llhao.gobang.service.IGameService;
import com.llhao.gobang.service.IUserService;
import com.llhao.gobang.service.po.Game;
import com.llhao.gobang.web.vo.ChessResultView;
import com.llhao.gobang.web.vo.NewGameView;
import com.llhao.gobang.web.vo.PlayChessView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by luohao on 2017/5/19.
 */
@Controller
public class GameConrtoller {
    @Autowired
    private IGameService gameService;
    @Autowired
    private IUserService userService;

    @RequestMapping("/step")
    public @ResponseBody ChessResultView step(@RequestParam int step, HttpSession session){
        Game game = (Game) session.getAttribute("game");
        //System.out.println(game+","+step);
        ChessResultView view = gameService.step(game,step-1);
        if(view.getCode()==200) {
            List<ChessNode> nodeList = game.getChess().getNodes();
            for (ChessNode chessNode : nodeList) {
                System.out.println(chessNode);
            }
            System.out.println(view);
        }
        return view;
    }

    @RequestMapping("play")
    public @ResponseBody PlayChessView play(@RequestParam int x, @RequestParam int y, int type, HttpSession session){
        User user = (User) session.getAttribute("user");
        Game game = (Game) session.getAttribute("game");
        PlayChessView view = new PlayChessView();
        if(user==null&&game==null){
            view.setCode(404);
            view.setMessage("没有登录或者没有在游戏中...");
            return view;
        }else {
            try {
                System.out.println("USRPLAY:"+x+","+y);
                return gameService.play(x, y, type, user, game);
            }catch (Exception e){
                view.setCode(500);
                view.setMessage(e.getMessage());
                return view;
            }
        }
    }

    @RequestMapping("/newGame")
    public @ResponseBody NewGameView newGame(@RequestParam int type, HttpSession session, ServletContext context){
        return null;
    }

    @RequestMapping("/newComputer")
    public @ResponseBody NewGameView newComputer(String str, int type, int diff, HttpSession session){
        User user = (User) session.getAttribute("user");
        //User user = userService.findById(2);
        User com = userService.findById(-diff);
        NewGameView view = new NewGameView();
        if(user==null){
            view.setCode(404);
            view.setMessage("please login first");
            return view;
        }
        Game game = new Game();
        DynamicEvaluation evaluation = new DynamicEvaluation();
        game.setChess(DynamicChess.fromDate(str,evaluation));
        game.setTime(new Timestamp(System.currentTimeMillis()));
        game.setBlack(type==1?user:com);
        game.setWhite(type==1?com:user);
        game.setNow(game.getMaxStep()%2==1?-1:1);
        game.init();
        session.setAttribute("game",game);
        view.setMessage("success");
        view.setCode(200);
        return view;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        GameConrtoller conrtoller = context.getBean(GameConrtoller.class);
        String data = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000BWB00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        NewGameView view = conrtoller.newComputer(data,1,2,null);
        System.out.println(view);
    }

}
