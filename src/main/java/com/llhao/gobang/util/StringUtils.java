package com.llhao.gobang.util;

/**
 * Created by llhao on 2017/4/25.
 */
public class StringUtils {
    public static boolean isEmail(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        String regex = "\\w+@\\w+(\\.\\w+)+";
        return str.matches(regex);
    }
}
