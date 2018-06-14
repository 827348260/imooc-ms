package com.chzero.imooc.ms.controller;

import com.chzero.imooc.ms.result.Result;
import com.chzero.imooc.ms.service.MSUserService;
import com.chzero.imooc.ms.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
    public Result<Boolean> doLogin(@Valid LoginVo loginVo, HttpServletResponse response){
        this.logger.info(loginVo.toString());
        boolean login = this.msUserService.login(loginVo, response);
        return Result.success(login);
    }

}
