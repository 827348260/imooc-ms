package com.chzero.imooc.ms.exception;

import com.chzero.imooc.ms.result.CodeMsg;
import lombok.Data;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-13 10:01
 * @email 827348260@qq.com
 * @description
 */
@Data
public class GlobalException extends RuntimeException{

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg){
        super(codeMsg.getMsg());
        this.codeMsg = codeMsg;
    }

    public GlobalException(String message){
        super(message);
    }

    public GlobalException(String message, Throwable cause){
        super(message, cause);
    }

    public GlobalException(Throwable cause){
        super(cause);
    }

    protected GlobalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
