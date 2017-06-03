package com.llhao.gobang.test;


/**
 * Created by luoaho on 2017/5/21.
 */
public class Test2 {
    public static void main(String[] args) {
       System.out.println(Math.log(100000000)/Math.log(2));
    }

    public static String toSimpleString(int i){
        if(i<=2){
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for(int k=i-1;k>=2;k--){
            builder.append(i%k+" ");
        }
        return builder.toString();
    }
}
