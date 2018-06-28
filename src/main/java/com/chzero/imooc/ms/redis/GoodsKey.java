package com.chzero.imooc.ms.redis;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-12 14:57
 * @email 827348260@qq.com
 * @description
 */
public class GoodsKey extends BasePrefix{

    private GoodsKey(String prefix){
        super(prefix);
    }

    public static GoodsKey getGoodsList = new GoodsKey("gl");
}
