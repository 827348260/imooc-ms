package com.chzero.imooc.ms.dao;

import com.chzero.imooc.ms.POJO.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-11 15:21
 * @email 827348260@qq.com
 * @description
 */
@Mapper
public interface UserDAO{

    @Select(value = "SELECT * FROM user WHERE id = #{idUser}")
    User getById(@Param(value = "idUser") String idUser);

    @Insert(value = "INSERT INTO user VALUES (#{id}, #{name})")
    int addUser(User user);
}
