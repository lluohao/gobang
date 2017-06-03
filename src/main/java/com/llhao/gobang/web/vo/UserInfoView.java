package com.llhao.gobang.web.vo;

/**
 * Created by luohao on 2017/6/2.
 */
public class UserInfoView extends BasicView{
    private String name;
    private int win;
    private int lose;
    private int tie;
    private int sum;

    public String getName() {
        return name;
    }

    public int getWin() {
        return win;
    }

    public int getLose() {
        return lose;
    }

    public int getTie() {
        return tie;
    }

    public int getSum() {
        return sum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public void setTie(int tie) {
        this.tie = tie;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
