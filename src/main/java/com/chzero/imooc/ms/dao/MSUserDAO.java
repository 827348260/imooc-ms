package com.chzero.imooc.ms.dao;

import com.chzero.imooc.ms.vo.MSUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-12 22:03
 * @email 827348260@qq.com
 * @description
 */
@Mapper
public interface MSUserDAO{

    @Select(value = "SELECT * FROM ms_user WHERE id = #{idUser}")
    MSUser getById(@Param(value = "idUser") long idUser);
}
