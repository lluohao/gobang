package com.llhao.gobang.ai.eval.finder;

/**
 * Created by llhao on 2017/4/22.
 */
public class KmpFinder implements Finder {
    public int find(int[] sqe, int[] target) {
        int[] next = getNext(target);
        int count = 0;
        for (int i = 0; i <= sqe.length - target.length; ) {
            boolean flag = true;
            int j = 0;
            for (; j < target.length; j++) {
                if (sqe[i + j] != target[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                count++;
                i++;
            } else {
                i += j - next[j];
            }
        }
        return count;
    }

    private int[] getNext(int[] t) {
        int[] next = new int[t.length];
        next[0] = -1;
        int suffix = 0;  // ��׺
        int prefix = -1;  // ǰ׺
        while (suffix < t.length - 1) {
            //��ǰ׺����Ϊ-1����ȣ���ǰ׺��׺������+1
            if (prefix == -1 || t[prefix] == t[suffix]) {
                ++prefix;
                ++suffix;
                next[suffix] = prefix;  //1
            } else {
                prefix = next[prefix];  //2
            }
        }
        return next;
    }

    public static void main(String[] args) {
        KmpFinder finder = new KmpFinder();
        System.out.println(finder.find(new int[]{1,2,3,4,5,6,7,1,0,1,1,1,2,3,4,1,0,1,1,1},new int[]{1,0,1,1,1}));
    }
}
