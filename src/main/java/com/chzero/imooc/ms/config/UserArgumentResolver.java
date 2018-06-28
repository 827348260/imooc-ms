package com.chzero.imooc.ms.config;

import com.chzero.imooc.ms.service.MSUserService;
import com.chzero.imooc.ms.util.ValueConst;
import com.chzero.imooc.ms.POJO.MSUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-13 12:21
 * @email 827348260@qq.com
 * @description
 */
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver{

    @Autowired
    private MSUserService msUserService;

    /**
     * 当类型为指定类型时执行后续操作
     * @param parameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter){
        Class<?> clazz = parameter.getParameterType();
        return clazz == MSUser.class;
    }

    /**
     * 自动注入MSUser对象参数
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception{

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);

        String parameToken = request.getParameter(ValueConst.COOKIE_NAME_TOKEN); //请求参数里面的token
        String cookieToken = this.getCookieValue(request, ValueConst.COOKIE_NAME_TOKEN); //cookie里面的token

        if (StringUtils.isBlank(cookieToken) && StringUtils.isBlank(parameToken)){
            return null;
        }
        String token = StringUtils.isBlank(parameToken) ? cookieToken : parameToken;
        return this.msUserService.getByToken(response, token);
    }

    /**
     * 获取指定name的Cookie值
     * @param request
     * @param tokenName
     * @return
     */
    private String getCookieValue(HttpServletRequest request, String tokenName){
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length <= 0){
            return null;
        }
        for (Cookie cookie : cookies){
            if (cookie.getName().equals(tokenName)){
                return cookie.getValue();
            }
        }
        return null;
    }
}
