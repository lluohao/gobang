package com.llhao.gobang.web.vo;

/**
 * Created by luohao on 2017/5/20.
 */
public class GameStatusView extends BasicView{
    private int status;
    private int type;

    public int getStatus() {
        return status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
