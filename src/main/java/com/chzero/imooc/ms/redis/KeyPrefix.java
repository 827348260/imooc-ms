package com.chzero.imooc.ms.redis;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-12 14:51
 * @email 827348260@qq.com
 * @description
 */
public interface KeyPrefix{

    int expireSeconds();

    String getPrefix();
}
