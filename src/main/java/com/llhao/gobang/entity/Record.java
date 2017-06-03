package com.llhao.gobang.entity;

import java.sql.Timestamp;

/**
 * Created by luohao on 2017/6/1.
 */
public class Record {
    private int rid;
    private int black;
    private int white;
    private int rwin;
    private Timestamp rtime;
    private String data;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getBlack() {
        return black;
    }

    public void setBlack(int black) {
        this.black = black;
    }

    public int getWhite() {
        return white;
    }

    public void setWhite(int white) {
        this.white = white;
    }

    public int getRwin() {
        return rwin;
    }

    public void setRwin(int rwin) {
        this.rwin = rwin;
    }

    public Timestamp getRtime() {
        return rtime;
    }

    public void setRtime(Timestamp rtime) {
        this.rtime = rtime;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Record{" +
                "rid=" + rid +
                ", black=" + black +
                ", white=" + white +
                ", rwin=" + rwin +
                ", rtime=" + rtime +
                ", data='" + data + '\'' +
                '}';
    }
}
