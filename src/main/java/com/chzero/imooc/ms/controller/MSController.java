package com.chzero.imooc.ms.controller;

import com.chzero.imooc.ms.POJO.MSOrder;
import com.chzero.imooc.ms.POJO.MSUser;
import com.chzero.imooc.ms.POJO.OrderInfo;
import com.chzero.imooc.ms.redis.RedisService;
import com.chzero.imooc.ms.result.CodeMsg;
import com.chzero.imooc.ms.service.GoodsService;
import com.chzero.imooc.ms.service.MSOrderService;
import com.chzero.imooc.ms.service.MSService;
import com.chzero.imooc.ms.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-13 23:28
 * @email 827348260@qq.com
 * @description
 */
@Controller
@RequestMapping(value = "ms")
public class MSController{

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private MSOrderService msOrderService;

    @Autowired
    private MSService msService;

    @RequestMapping(value = "do_miaosha")
    public String toList(@RequestParam(value = "idGoods") long idGoods, Model model, MSUser msUser){
        if (msUser == null){
            return "login";
        }
        //判断库存
        GoodsVo goodsVo = this.goodsService.getGoodsVoByIdGoods(idGoods);
        int stockCount = goodsVo.getStockCount();
        if (stockCount <= 0){
            model.addAttribute("errmsg", CodeMsg.MS_OVER.getMsg());
            return "miaosha_fail";
        }

        //判断是否已经秒杀
        MSOrder userGoods = this.msOrderService.getMSOrderByIdUserIdGoods(msUser.getId(), idGoods);
        if (userGoods != null){
            model.addAttribute("errmsg", CodeMsg.REPETE_MIAOSHA.getMsg());
            return "miaosha_fail";
        }

        //减库存, 下订单, 写入秒杀单
        OrderInfo orderInfo = this.msService.miaosha(msUser, goodsVo);
        model.addAttribute("user", msUser);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goodsVo);

        return "order_detail";
    }


}
