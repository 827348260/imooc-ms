package com.chzero.imooc.ms.service;

import com.chzero.imooc.ms.POJO.MSGoods;
import com.chzero.imooc.ms.dao.GoodsDAO;
import com.chzero.imooc.ms.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-13 14:16
 * @email 827348260@qq.com
 * @description
 */
@Service
public class GoodsService{

    @Autowired
    private GoodsDAO goodsDAO;

    public List<GoodsVo> listGoodsVo(){
        return this.goodsDAO.listGoods();
    }


    public GoodsVo getGoodsVoByIdGoods(long idGoods){
        return this.goodsDAO.getGoodsVoByIdGoods(idGoods);
    }


    public void reduceStock(GoodsVo goodsVo){
        MSGoods goods = new MSGoods();
        goods.setId(goodsVo.getId());
        int i = goodsDAO.reduceStock(goods);
    }
}
