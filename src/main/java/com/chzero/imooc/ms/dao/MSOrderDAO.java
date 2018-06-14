package com.chzero.imooc.ms.dao;

import com.chzero.imooc.ms.POJO.MSOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-13 14:15
 * @email 827348260@qq.com
 * @description
 */
@Mapper
public interface MSOrderDAO{

    @Select(value = "SELECT * FROM ms_order WHERE id_user = #{idUser} AND id_goods = #{idGoods}")
    MSOrder getMSOrderByIdUserIdGoods(@Param(value = "idUser") long idUser, @Param(value = "idGoods") long idGoods);

    @Insert(value = "INSERT INTO ms_order(id_user, id_order, id_goods) VALUES (#{idUser}, #{idOrder}, #{idGoods})")
    int insertMSOrder(MSOrder msOrder);
}
