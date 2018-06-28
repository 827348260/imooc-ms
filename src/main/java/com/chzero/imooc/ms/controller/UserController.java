package com.chzero.imooc.ms.controller;

import com.chzero.imooc.ms.POJO.MSUser;
import com.chzero.imooc.ms.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-14 15:37
 * @email 827348260@qq.com
 * @description
 */
@Controller
@RequestMapping(value = "user")
public class UserController{

    @ResponseBody
    @RequestMapping(value = "info")
    public Result<MSUser> toList(Model model, MSUser msUser){
        //查询商品列表
        return Result.success(msUser);
    }

}
