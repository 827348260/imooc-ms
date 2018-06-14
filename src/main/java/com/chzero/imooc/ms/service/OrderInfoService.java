package com.chzero.imooc.ms.service;

import com.chzero.imooc.ms.POJO.MSOrder;
import com.chzero.imooc.ms.POJO.MSUser;
import com.chzero.imooc.ms.POJO.OrderInfo;
import com.chzero.imooc.ms.dao.MSOrderDAO;
import com.chzero.imooc.ms.dao.OrderInfoDAO;
import com.chzero.imooc.ms.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-14 10:49
 * @email 827348260@qq.com
 * @description
 */
@Service
public class OrderInfoService{

    @Autowired
    private OrderInfoDAO orderInfoDAO;

    @Autowired
    private MSOrderDAO msOrderDAO;

    @Transactional
    public OrderInfo createOrder(MSUser msUser, GoodsVo goodsVo){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(LocalDateTime.now());
        orderInfo.setIdDeliveryAddr(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setIdGoods(goodsVo.getId());
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsPrice(goodsVo.getMsPrice());
        orderInfo.setOrderChannel(3);
        orderInfo.setStatus(0);
        orderInfo.setIdUser(msUser.getId());
        long idOrder = this.orderInfoDAO.insert(orderInfo);
        MSOrder msOrder = new MSOrder();
        msOrder.setIdOrder(idOrder);
        msOrder.setIdGoods(goodsVo.getId());
        msOrder.setIdUser(msUser.getId());
        int i = this.msOrderDAO.insertMSOrder(msOrder);
        return orderInfo;
    }
}
