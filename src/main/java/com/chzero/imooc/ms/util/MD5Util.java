package com.chzero.imooc.ms.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-12 17:18
 * @email 827348260@qq.com
 * @description
 */
public class MD5Util{

    private static final String salt = "!@#$%^&*()1234567890q%^&*wERt%^&*yUI%^&*o";

    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    public static String inputPassFromPass(String inputPass){
        String stringBuilder = String.valueOf(salt.charAt(5)) + salt.charAt(2) + salt.charAt(2) + inputPass + salt.charAt(3) + salt.charAt(6) + salt.charAt(1);
        return md5(stringBuilder);
    }

    public static String fromPassToDBPass(String fromPass, String salt){
        String stringBuilder = String.valueOf(salt.charAt(9)) + salt.charAt(8) + salt.charAt(5) + fromPass + salt.charAt(2) + salt.charAt(1) + salt.charAt(1);
        return md5(stringBuilder);
    }

    public static String inputPassToDBPass(String input, String saltDB){
        String inputPass = inputPassFromPass(input);
        return fromPassToDBPass(inputPass, saltDB);
    }

    public static void main(String[] args){
        System.out.println(inputPassFromPass("123456"));
        System.out.println(fromPassToDBPass("f867b2e872b3a8ea5ed94c21d28b56ed","1234567890"));
        System.out.println(inputPassToDBPass("123456", "1234567890"));
    }

}
