package com.ducnd.demo;

/**
 * Created by ducnd on 6/11/17.
 */
public class StringUtils {
    public static boolean isBlank(String content) {
        if ( null == content || "".equals(content)) {
            return true;
        }
        return false;
    }
}
