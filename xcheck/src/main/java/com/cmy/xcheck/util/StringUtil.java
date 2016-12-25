package com.cmy.xcheck.util;

/**
 * @Author chenjw
 * @Date 2016年12月23日
 */
public class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

}
