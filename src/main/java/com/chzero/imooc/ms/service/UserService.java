package com.chzero.imooc.ms.service;

import com.chzero.imooc.ms.dao.UserDAO;
import com.chzero.imooc.ms.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-11 15:27
 * @email 827348260@qq.com
 * @description
 */
@Service
public class UserService{

    @Autowired
    private UserDAO userDAO;

    public User getById(String idUser){
        return this.userDAO.getById(idUser);
    }

    public int addUser(User user){
        return this.userDAO.addUser(user);
    }

}
