package com.llhao.gobang.ai.eval;

import java.util.ArrayList;

/**
 * Created by luohao on 2017/5/25.
 */
public class FeatureUtils {
    public static void main(String[] args) {
        ArrayList<Integer> ss = new ArrayList<>();
        ss.add(3);
        while (ss.size()<100){
            for(int i = ss.get(ss.size()-1)+1;true;i++){
                if(isSimple(i)){
                    ss.add(i);
                    break;
                }
            }
        }
        for(int i = 1;i<ss.size()-1;i++){
            for(int j = i+1;j<ss.size();j++){
                boolean isOk = false;
                int a = ss.get(i);
                int b = ss.get(j);
                int c = 2;
                for(int k = 0;k<i;k++){
                    int d = ss.get(k);
                    if(isSimple(a+b)||isSimple(a+c)||isSimple(a+d)||isSimple(b+c)||isSimple(b+d)){
                        isOk = true;
                    }
                }
                if(!isOk){
                    System.out.println(a+","+b+","+c+",");
                }
            }
        }
        System.out.println(ss);
    }

    public static boolean isSimple(int x){
        for(int i = 2;i<=Math.sqrt(x);i++){
            if(x/i*i==x){
                return false;
            }
        }
        return true;
    }
}
