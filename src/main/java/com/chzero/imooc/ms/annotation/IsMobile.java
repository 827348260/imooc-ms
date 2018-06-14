package com.chzero.imooc.ms.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-13 9:25
 * @email 827348260@qq.com
 * @description
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {IsMobileValidator.class})
public @interface IsMobile{

    boolean required() default true;

    String message() default "手机号码格式有误.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
