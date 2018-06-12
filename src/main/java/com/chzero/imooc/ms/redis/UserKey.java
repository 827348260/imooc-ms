package com.chzero.imooc.ms.redis;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-12 14:57
 * @email 827348260@qq.com
 * @description
 */
public class UserKey extends BasePrefix{

    private UserKey(String prefix){
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}
