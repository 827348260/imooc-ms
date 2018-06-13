package com.chzero.imooc.ms.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-12 21:51
 * @email 827348260@qq.com
 * @description
 */
public class ValidatorUtil{

    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String src){
        if (StringUtils.isBlank(src)){ return false; }
        Matcher matcher = mobile_pattern.matcher(src);
        return matcher.matches();
    }

    public static void main(String[] args){
        System.out.println(isMobile("15761686535"));
    }

}
