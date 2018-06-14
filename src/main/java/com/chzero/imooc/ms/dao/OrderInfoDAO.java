package com.chzero.imooc.ms.dao;

import com.chzero.imooc.ms.POJO.OrderInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-13 14:15
 * @email 827348260@qq.com
 * @description
 */
@Mapper
public interface OrderInfoDAO{

    @Insert(value = "INSERT INTO order_info(id_user, id_goods, id_delivery_addr, goods_name, goods_count, goods_price, order_channel, status, create_date) " + "VALUES (#{idUser}, #{idGoods}, #{idDeliveryAddr}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel}, #{status},  #{createDate} )")
    @SelectKey(keyColumn = "id", keyProperty = "id", resultType = long.class, before = false, statement = "SELECT last_insert_id()")
    long insert(OrderInfo orderInfo);
}
