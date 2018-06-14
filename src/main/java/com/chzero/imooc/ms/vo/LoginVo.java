package com.chzero.imooc.ms.vo;

import com.chzero.imooc.ms.annotation.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-12 20:02
 * @email 827348260@qq.com
 * @description
 */
@Data
public class LoginVo{

    @NotNull(message = "手机号码不能为空.")
    @IsMobile(message = "手机号码格式不正确")
    private String mobile;

    @NotNull(message = "密码不能为空.")
    @Length(min = 32, max = 32, message = "密码格式不正确")
    private String password;
}
