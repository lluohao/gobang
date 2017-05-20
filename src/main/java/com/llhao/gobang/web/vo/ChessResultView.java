package com.llhao.gobang.web.vo;

/**
 * Created by luohao on 2017/5/19.
 */
public class ChessResultView {
    private int x;
    private int y;
    private int code;
    private int win;
    private String message;

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ChessResultView{" +
                "x=" + x +
                ", y=" + y +
                ", code=" + code +
                ", win=" + win +
                ", message='" + message + '\'' +
                '}';
    }
}
