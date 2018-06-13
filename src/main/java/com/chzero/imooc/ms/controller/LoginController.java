package com.chzero.imooc.ms.controller;

import com.chzero.imooc.ms.result.CodeMsg;
import com.chzero.imooc.ms.result.Result;
import com.chzero.imooc.ms.service.MSUserService;
import com.chzero.imooc.ms.util.ValidatorUtil;
import com.chzero.imooc.ms.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-12 20:03
 * @email 827348260@qq.com
 * @description
 */
@Controller
@RequestMapping(value = "login")
public class LoginController{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MSUserService msUserService;

    @RequestMapping(value = "to_login")
    public String toLogin(){
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "do_login")
    public Result<CodeMsg> doLogin(LoginVo loginVo){
        this.logger.info(loginVo.toString());
        String inputPass = loginVo.getPassword();
        String mobile = loginVo.getMobile();
        if (StringUtils.isBlank(inputPass)){
            return Result.error(CodeMsg.PASSWORD_EMPTY);
        }
        if (StringUtils.isBlank(mobile)){
            return Result.error(CodeMsg.MOBILE_EMPTY);
        }
        if (!ValidatorUtil.isMobile(mobile)){
            return Result.error(CodeMsg.MOBILE_ERROR);
        }
        CodeMsg codeMsg = this.msUserService.login(loginVo);
        return Result.success(codeMsg);
    }

}
