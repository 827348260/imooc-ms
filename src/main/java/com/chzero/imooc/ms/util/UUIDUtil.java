package com.chzero.imooc.ms.util;

import java.util.UUID;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-13 10:22
 * @email 827348260@qq.com
 * @description
 */
public class UUIDUtil{
    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
