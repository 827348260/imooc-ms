package com.chzero.imooc.ms.controller;

import com.chzero.imooc.ms.POJO.MSUser;
import com.chzero.imooc.ms.service.GoodsService;
import com.chzero.imooc.ms.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-13 10:33
 * @email 827348260@qq.com
 * @description
 */
@Controller
@RequestMapping(value = "goods")
public class GoodsController{

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "to_list")
    public String toList(Model model, MSUser msUser){
        //查询商品列表
        List<GoodsVo> goodsList = this.goodsService.listGoodsVo();
        model.addAttribute("user", msUser);
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }

    @RequestMapping(value = "to_detail/{idGoods}")
    public String detail(@PathVariable(value = "idGoods") long idGoods, Model model, MSUser msUser){
        GoodsVo goodsVo = this.goodsService.getGoodsVoByIdGoods(idGoods);

        LocalDateTime startDate = goodsVo.getStartDate();
        LocalDateTime endDate = goodsVo.getEndDate();
        LocalDateTime now = LocalDateTime.now();

        int msStatus = 0;
        long remainSeconds = 0;
        Duration duration = null;
        duration = Duration.between(startDate, endDate);
        if (now.isBefore(startDate)){ //秒杀未开始
            msStatus = 0;
            remainSeconds = duration.getSeconds();
        }else if (now.isAfter(endDate)){ //秒杀已结束
            msStatus = 2;
            remainSeconds = -1;
        }else{ //秒杀进行中
            msStatus = 1;
            remainSeconds = 0;
        }

        model.addAttribute("user", msUser);
        model.addAttribute("goods", goodsVo);
        model.addAttribute("msStatus", msStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }
}
