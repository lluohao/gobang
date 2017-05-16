package com.llhao.gobang.test;

import com.llhao.gobang.ai.MinmaxAI;
import com.llhao.gobang.ai.ResultNode;
import com.llhao.gobang.ai.eval.DynamicEvaluation;
import com.llhao.gobang.ai.record.ZobristRecord;
import com.llhao.gobang.ai.util.ResultNodeUtils;
import com.llhao.gobang.chess.DynamicChess;
import com.llhao.gobang.utils.Matrixs;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by llhao on 2017/4/23.
 */
public class Test {
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
                {0, 0, 0, 0, 0, 0, 0, 1, -1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        },evaluation);
        MinmaxAI ai = new MinmaxAI();
        evaluation.setAttack(false);
        ResultNode next = null;
//        next = ai.next(chess,1,4);
//        System.out.println(next);
       // debug(next);
        for (int i = 0; i < 10; i++) {
            ZobristRecord.count=0;
            long start = System.currentTimeMillis();
            next = null;
            if(i%2==0){
                next = ai.next(chess, -1, 4);
                chess.play(next.getX(), next.getY(), -1);
                System.out.println("本次搜索用时："+(System.currentTimeMillis()-start)+"毫秒"+",搜索結點數："+ ResultNodeUtils.countChildren(next.getParent())+",置换表加速："+ZobristRecord.count);
                ResultNode temp = next;
                while (temp.getNext()!=null){
                    temp = temp.getNext();
                }
                System.out.println(temp);
            }else{
                next = ai.next(chess, 1, 4);
                chess.play(next.getX(), next.getY(), 1);
                System.out.println(next.getX() + "," + next.getY() + "," + next.getScore()+",搜索节点数："+ ResultNodeUtils.countChildren(next.getParent()));
            }
            ResultNode temp = next;
            while (temp.getNext()!=null){
                temp = temp.getNext();
            }
            System.out.println(next);
            System.out.println("*\t0\t1\t2\t3\t4\t5\t6\t7\t8\t9\t10\t11\t12\t13\t14\t");
            Matrixs.print(chess.getNodes());
            System.out.println();
            if(evaluation.win(chess)==1){
                System.out.println("BLACK WIN");
                break;
            }else if(evaluation.win(chess)==-1){
                System.out.println("WHITE WIN");
                break;
            }
        }
    }

    public static void debug(ResultNode next){
        ResultNode root = next.getParent();
        List<ResultNode> cs = root.getChildren();
        ResultNode[] css = new ResultNode[cs.size()];
        Collections.sort(cs, new Comparator<ResultNode>() {
            @Override
            public int compare(ResultNode o1, ResultNode o2) {
                return o1.getScore()-o2.getScore();
            }
        });
        int i = 0;
        for (ResultNode c : cs) {
            System.out.println(c+"::::"+c.getScore());
            css[i++] = c;
        }
        new DebugFrame(css);
    }
}
