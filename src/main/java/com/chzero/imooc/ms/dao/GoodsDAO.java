package com.chzero.imooc.ms.dao;

import com.chzero.imooc.ms.POJO.MSGoods;
import com.chzero.imooc.ms.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-13 14:14
 * @email 827348260@qq.com
 * @description
 */
@Mapper
public interface GoodsDAO{

    @Select(value = "SELECT goods.*, ms_goods.stock_count, ms_goods.start_date, ms_goods.end_date, ms_goods.ms_price FROM goods LEFT JOIN ms_goods ON goods.id = ms_goods.id_goods")
    List<GoodsVo> listGoods();

    @Select(value = "SELECT goods.*, ms_goods.stock_count, ms_goods.start_date, ms_goods.end_date, ms_goods.ms_price FROM goods LEFT JOIN ms_goods ON goods.id = ms_goods.id_goods AND goods.id = #{idGoods}")
    GoodsVo getGoodsVoByIdGoods(long idGoods);

    @Update(value = "UPDATE ms_goods SET stock_count = stock_count - 1 WHERE id_goods = #{id}")
    int reduceStock(MSGoods msGoods);
}
