package com.chzero.imooc.ms.redis;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-12 14:57
 * @email 827348260@qq.com
 * @description
 */
public class MSUserKey extends BasePrefix{

    private static final int TOKEN_EXPIRE = 1800; //秒

    private MSUserKey(int expireSeconds, String prefix){
        super(expireSeconds, prefix);
    }

    public static MSUserKey token = new MSUserKey(TOKEN_EXPIRE, "token");
}
