package com.llhao.gobang.chess;

import com.llhao.gobang.ai.eval.DynamicEvaluation;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

/**
 * Created by luohao on 2017/5/4.
 */
public class DynamicChess extends Chess {
    private int[][] shapLine = new int[15][15];
    private int[][] shapRow = new int[15][15];
    private int[][] shapLeft = new int[15][15];
    private int[][] shapRight = new int[15][15];
    private int[] blackCount = new int[16];
    private int[] whiteCount = new int[16];
    private DynamicEvaluation evaluation;

    public DynamicChess(DynamicEvaluation evaluation) {
        this.evaluation = evaluation;
    }

    public DynamicChess(){
        this(DynamicEvaluation.evaluation);
    }

    public boolean play(int x, int y, int type){
        super.play(x,y,type);
        update(x,y);
        return true;
    }

    private void updateCount(int i,int value){
        if(i==0){
            return;
        }else if(i==100){
            blackCount[0]+=value;
        }else if(i==-100){
            whiteCount[0]+=value;
        }else if(i>0){
            blackCount[i]+=value;
        }else{
            whiteCount[-i]+=value;
        }
    }
    private void update(int x,int y){
        //update line
        for(int ix = Math.max(0,x-6);ix<=Math.min(x,9);ix++){
            updateCount(shapLine[y][ix],-1);
            shapLine[y][ix] = evaluation.shapType(this,ix,y,1,0);
            updateCount(shapLine[y][ix],1);
        }
        //update row
        for(int iy = Math.max(0,y-6);iy<=Math.min(y,9);iy++){
            updateCount(shapRow[iy][x],-1);
            shapRow[iy][x] = evaluation.shapType(this,x,iy,0,1);
            updateCount(shapRow[iy][x],1);
        }
        //update right
        for(int i = 0;i<6;i++){
            int rx = x-i;
            int ry = y-i;
            if(rx>=0&&ry>=0) {
                updateCount(shapRight[ry][rx],-1);
                shapRight[ry][rx] = evaluation.shapType(this, rx, ry, 1, 1);
                updateCount(shapRight[ry][rx],1);
            }
        }
        //update left
        for(int i = 0;i<6;i++){
            int rx = x-i;
            int ry = y+i;
            if(rx>=0&&ry<=14) {
                updateCount(shapLeft[ry][rx],-1);
                shapLeft[ry][rx] = evaluation.shapType(this, rx, ry, 1, -1);
                updateCount(shapLeft[ry][rx],1);
            }
        }
    }

    public int[] getBlackCount() {
        return blackCount;
    }

    public int[] getWhiteCount() {
        return whiteCount;
    }

    public int[][] getShapLine() {
        return shapLine;
    }

    public int[][] getShapRow() {
        return shapRow;
    }

    public int[][] getShapLeft() {
        return shapLeft;
    }

    public int[][] getShapRight() {
        return shapRight;
    }

    public static DynamicChess fromDate(int[][] data, DynamicEvaluation evaluation) {
        DynamicChess chess = new DynamicChess(evaluation);
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != 0) {
                    chess.play(j, i, data[i][j]);
                }
            }
        }
        return chess;
    }

    public static DynamicChess fromDate(String str,DynamicEvaluation evaluation){
        if(str==null||str.length()!=225){
            System.out.println(str+":"+str.length());
            return new DynamicChess(evaluation);
        }
        int[][] data = new int[15][15];
        ByteArrayInputStream bis = new ByteArrayInputStream(str.getBytes());
        for(int i = 0;i<15*15;i++){
            char c = (char)bis.read();
            data[i/15][i%15] = c=='0'?0:c=='B'?1:-1;
        }
        return fromDate(data,evaluation);
    }

    public static void main(String[] args) {
        DynamicEvaluation evaluation = new DynamicEvaluation();
        DynamicChess chess = DynamicChess.fromDate(new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, -1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        },evaluation);
        int[] mycount = new int[16];
        int[] otcount = new int[16];
        evaluation.count(chess,mycount,otcount,1);
        System.err.println(Arrays.toString(mycount));
        System.out.println(Arrays.toString(otcount));
    }
}
