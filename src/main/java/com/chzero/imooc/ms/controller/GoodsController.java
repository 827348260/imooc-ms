package com.chzero.imooc.ms.controller;

import com.chzero.imooc.ms.POJO.MSUser;
import com.chzero.imooc.ms.redis.GoodsKey;
import com.chzero.imooc.ms.redis.RedisService;
import com.chzero.imooc.ms.service.GoodsService;
import com.chzero.imooc.ms.vo.GoodsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private RedisService redisService;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    ApplicationContext applicationContext;

    @RequestMapping(value = "to_list")
    public String toList(HttpServletRequest request, HttpServletResponse response, Model model, MSUser msUser){
        //查询商品列表
        List<GoodsVo> goodsList = this.goodsService.listGoodsVo();

        //取缓存
        String html = redisService.get(GoodsKey.getGoodsList, "", String.class);
        if(!StringUtils.isEmpty(html)) {
            return html;
        }

        model.addAttribute("user", msUser);
        model.addAttribute("goodsList", goodsList);

        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext);
        //手动渲染
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx);
        if(!StringUtils.isEmpty(html)) {
            redisService.set(GoodsKey.getGoodsList, "", html);
        }
        return html;

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
