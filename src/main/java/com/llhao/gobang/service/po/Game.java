package com.llhao.gobang.service.po;

import com.llhao.gobang.ai.MinmaxAI;
import com.llhao.gobang.ai.ResultNode;
import com.llhao.gobang.ai.eval.DynamicEvaluation;
import com.llhao.gobang.chess.ChessNode;
import com.llhao.gobang.chess.DynamicChess;
import com.llhao.gobang.entity.User;

import java.sql.Timestamp;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by luohao on 2017/5/19.
 */
public class Game {
    private User black;
    private User white;
    private int win;
    private Timestamp time;
    private int maxStep;
    private DynamicChess chess;
    private int now;
    private ExecutorService threadPool = Executors.newCachedThreadPool();
    private static DynamicEvaluation evaluation = new DynamicEvaluation();
    private static MinmaxAI ai = new MinmaxAI();
    private boolean save = false;

    public void init(){
        if(getUser(now).getId()<0){
            computerNext();
        }
    }

    public boolean isSave() {
        return save;
    }

    public void setSave(boolean save) {
        this.save = save;
    }

    public boolean play(int x, int y, int type){
        if(win!=0){
            throw new RuntimeException("游戏已经结束,"+(win==1?"黑色方":"白色方")+"胜");
        }
        if(now==type) {
            chess.play(x, y, type);
            checkWin();
            now = -now;
            if(getUser(now).getId()<0){
                computerNext();
            }
            return true;
        }else{
            return false;
        }
    }

    public String getDate(){
        StringBuilder builder = new StringBuilder();
        int[][] data = chess.getSquare();
        for (int[] datum : data) {
            for (int i : datum) {
                builder.append(i==1?"B":i==-1?"W":"0");
            }
        }
        return builder.toString();
    }

    private void checkWin(){
        win = evaluation.win(chess);
    }

    private void computerNext(){
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                User user = getUser(now);
                int deep = -user.getId();
                ResultNode result = ai.next(chess,now,deep);
                chess.play(result.getX(),result.getY(),now);
                System.out.println("COMPLAY"+deep+":"+result.getX()+","+result.getY());
                //Matrixs.print(chess.getSquare());
                Game.this.setWin(evaluation.win(chess));
                now = -now;
            }
        });
    }

    public ChessNode getStep(int index){
        if(index<0||index>=getMaxStep()){
            throw new RuntimeException("not found this step");
        }else{
            ChessNode result = chess.getNodes().get(index);
            return result;
        }
    }

    public User getUser(int type){
        return type==1?getBlack():getWhite();
    }

    public int getNow() {
        return now;
    }

    public void setNow(int now) {
        this.now = now;
    }

    public User getBlack() {
        return black;
    }

    public void setBlack(User black) {
        this.black = black;
    }

    public User getWhite() {
        return white;
    }

    public void setWhite(User white) {
        this.white = white;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getMaxStep() {
        return chess.getNodes().size();
    }

    public void setMaxStep(int maxStep) {
        this.maxStep = maxStep;
    }

    public DynamicChess getChess() {
        return chess;
    }

    public void setChess(DynamicChess chess) {
        this.chess = chess;
    }
}
