package com.chzero.imooc.ms.service;

import com.chzero.imooc.ms.dao.MSUserDAO;
import com.chzero.imooc.ms.exception.GlobalException;
import com.chzero.imooc.ms.redis.MSUserKey;
import com.chzero.imooc.ms.redis.RedisService;
import com.chzero.imooc.ms.result.CodeMsg;
import com.chzero.imooc.ms.util.MD5Util;
import com.chzero.imooc.ms.util.UUIDUtil;
import com.chzero.imooc.ms.util.ValueConst;
import com.chzero.imooc.ms.vo.LoginVo;
import com.chzero.imooc.ms.POJO.MSUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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

    @Autowired
    private RedisService redisService;

    public MSUser getById(long idUser){
        return this.msUserDAO.getById(idUser);
    }

    public String login(LoginVo loginVo, HttpServletResponse response){
        if (loginVo == null){
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }

        String mobile = loginVo.getMobile();
        String inputPass = loginVo.getPassword();

        MSUser msUser = this.getById(Long.parseLong(mobile));
        if (msUser == null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }

        String dbPass = msUser.getPassword();
        String dbSalt = msUser.getSalt();

        String saltPass = MD5Util.fromPassToDBPass(inputPass, dbSalt);
        if (!saltPass.equals(dbPass)){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        String token = UUIDUtil.uuid();
        this.autoGenCookie(response, token, msUser);
        return token;
    }

    /**
     * 延长token有效期
     * @param token
     * @return
     */
    public MSUser getByToken(HttpServletResponse response, String token){
        if (StringUtils.isBlank(token)){
            return null;
        }

        MSUser msUser = this.redisService.get(MSUserKey.token, token, MSUser.class);
        if (msUser != null){
            this.autoGenCookie(response, token, msUser);
        }
        return msUser;
    }

    /**
     * 生成token
     * @param response
     * @param msUser
     */
    private void autoGenCookie(HttpServletResponse response, String token ,MSUser msUser){
        this.redisService.set(MSUserKey.token, token, msUser);
        Cookie cookie = new Cookie(ValueConst.COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(MSUserKey.token.expireSeconds()); //设置过期时间
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
