package com.chzero.imooc.ms.annotation;

import com.chzero.imooc.ms.util.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-13 9:29
 * @email 827348260@qq.com
 * @description
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String>{

    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation){
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        if (this.required){
            return ValidatorUtil.isMobile(value); //必须
        }else{
            return StringUtils.isBlank(value) || ValidatorUtil.isMobile(value); //非必须
        }
    }
}
