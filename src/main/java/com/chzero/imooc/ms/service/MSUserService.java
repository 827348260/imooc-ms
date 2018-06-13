package com.chzero.imooc.ms.service;

import com.chzero.imooc.ms.dao.MSUserDAO;
import com.chzero.imooc.ms.result.CodeMsg;
import com.chzero.imooc.ms.util.MD5Util;
import com.chzero.imooc.ms.vo.LoginVo;
import com.chzero.imooc.ms.vo.MSUser;
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
public class MSUserService{

    @Autowired
    private MSUserDAO msUserDAO;

    public MSUser getById(long idUser){
        return this.msUserDAO.getById(idUser);
    }

    public CodeMsg login(LoginVo loginVo){
        if (loginVo == null){
            return CodeMsg.SERVER_ERROR;
        }

        String mobile = loginVo.getMobile();
        String inputPass = loginVo.getPassword();

        MSUser msUser = this.getById(Long.parseLong(mobile));
        if (msUser == null){
            return CodeMsg.MOBILE_NOT_EXIST;
        }

        String dbPass = msUser.getPassword();
        String dbSalt = msUser.getSalt();

        String saltPass = MD5Util.fromPassToDBPass(inputPass, dbSalt);
        if (!saltPass.equals(dbPass)){
            return CodeMsg.PASSWORD_ERROR;
        }
        return CodeMsg.SUCCESS;
    }
}
