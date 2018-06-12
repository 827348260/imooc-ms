package com.chzero.imooc.ms.redis;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-12 14:58
 * @email 827348260@qq.com
 * @description
 */
public class OrderKey extends BasePrefix{

    private OrderKey(int expireSeconds, String prefix){
        super(expireSeconds, prefix);
    }

}
