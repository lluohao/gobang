package com.llhao.gobang.ai.sort;

import com.llhao.gobang.ai.eval.DynamicEvaluation;
import com.llhao.gobang.chess.Chess;
import com.llhao.gobang.chess.DynamicChess;
import com.llhao.gobang.chess.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Everthing-- on 2017/5/5.
 */
public class ThreatSorter implements Sorter {
    private SimpleMatrixSorter simpleMatrixSorter = new SimpleMatrixSorter();
    @Override
    public List<Point> buildPoints(Chess c) {
        List<Point> points = new ArrayList<>();
        if(c instanceof DynamicChess){
            DynamicChess chess = (DynamicChess) c;
            Map<Point,Integer> maps = new HashMap<>();
            build(chess.getShapLine(),1,0,maps);
            build(chess.getShapRow(),0,1,maps);
            build(chess.getShapLeft(),1,-1,maps);
            build(chess.getShapRight(),1,1,maps);
            List<Point> all = simpleMatrixSorter.buildPoints(c);
            for (Point point : all) {
                Integer old = maps.get(point);
                if(old==null){
                    maps.put(point,point.getValue());
                }else {
                    point.setValue(old+point.getValue()*100);
                    maps.put(point,point.getValue());
                }
            }
            Set<Point> keys = maps.keySet();
            for (Point key : keys) {
                key.setValue(maps.get(key));
            }
            points.addAll(keys);
            Collections.sort(points);
            return points;
        }
        return simpleMatrixSorter.buildPoints(c);
    }

    public static final int[][] TYPE_BLACK = {
            {1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 0},
            {0, 1, 1, 1, 0, 0}, {0, 0, 1, 1, 1, 0}, {0, 1, 0, 1, 1, 0}, {0, 1, 1, 0, 1, 0},
            {1, 1, 1, 1, 0}, {0, 1, 1, 1, 1}, {1, 1, 0, 1, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1},
            {0, 0, 1, 1, 0, 0}, {0, 0, 1, 0, 1, 0}, {0, 1, 0, 1, 0, 0}, {0, 0, 0, 1, 0, 0}, {0, 0, 1, 0, 0, 0}
    };

    public static final int[] COUNT = {5,4,3,3,3,3,4,4,4,4,4,2,2,2,1,1};

    private void build(int[][] data,int dx,int dy,Map<Point,Integer> points){
        for(int y = 0;y<data.length;y++){
            for(int x = 0;x<data[y].length;x++){
                int value = Math.abs(data[y][x]);
                if(value==0||value==100){
                    continue;
                }
                int count = COUNT[value];
                for(int i = 0;i<TYPE_BLACK[value].length;i++){
                    if(TYPE_BLACK[value][i]==0){
                        Point point = new Point(x+dx*i,y+dy*i);
                        Integer old = points.get(point);
                        if(old==null){
                            points.put(point,count*1000);
                            point.setValue(count*1000);
                        }else{
                            points.put(point,Math.max(old,count*1000));
                            point.setValue(Math.max(old,count*1000));
                        }
                    }
                }
            }
        }
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
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        },evaluation);
        Sorter sorter = new ThreatSorter();
        List<Point> points = sorter.buildPoints(chess);
        for (Point point : points) {
            System.out.println(point);
        }
    }

}
