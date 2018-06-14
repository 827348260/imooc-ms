package com.chzero.imooc.ms.exception;

import com.chzero.imooc.ms.result.CodeMsg;
import com.chzero.imooc.ms.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-13 9:44
 * @email 827348260@qq.com
 * @description
 */
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception exception){

        exception.printStackTrace();

        if (exception instanceof BindException){
            BindException bindException = (BindException)exception;
            List<ObjectError> errorList = bindException.getAllErrors();
            List<String> messageList = new ArrayList<>();
            for (ObjectError itemError : errorList){
                messageList.add(itemError.getDefaultMessage());
            }
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(messageList));
        }else if (exception instanceof GlobalException){
            GlobalException globalException = (GlobalException)exception;
            return Result.error(globalException.getCodeMsg());
        }
        return Result.error(CodeMsg.SERVER_ERROR);
    }

}
