package com.chzero.imooc.ms.service;

import com.chzero.imooc.ms.POJO.MSUser;
import com.chzero.imooc.ms.POJO.OrderInfo;
import com.chzero.imooc.ms.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-14 11:01
 * @email 827348260@qq.com
 * @description
 */
@Service
public class MSService{

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderInfoService orderInfoService;

    @Transactional
    public OrderInfo miaosha(MSUser msUser, GoodsVo goodsVo){
        //减库存, 下订单, 写入秒杀单
        this.goodsService.reduceStock(goodsVo);
        return this.orderInfoService.createOrder(msUser, goodsVo);
    }
}
