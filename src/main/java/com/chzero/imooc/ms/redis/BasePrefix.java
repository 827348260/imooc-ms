package com.chzero.imooc.ms.redis;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-12 14:52
 * @email 827348260@qq.com
 * @description
 */
public abstract class BasePrefix implements KeyPrefix{

    private int expireSeconds;

    private String prefix;

    public BasePrefix(String prefix){
        this(0, prefix);
    }

    public BasePrefix(int expireSeconds, String prefix){
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds(){ //默认0  代表永不过期
        return this.expireSeconds;
    }

    @Override
    public String getPrefix(){
        String simpleName = this.getClass().getSimpleName();
        return simpleName + ":" + this.prefix;
    }
}
