package com.chzero.imooc.ms.POJO;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-12 22:01
 * @email 827348260@qq.com
 * @description
 */
@Data
public class MSUser{

    private Long id;
    private String nickName;
    private String password;
    private String salt;
    private String head;
    private LocalDateTime registerDate;
    private LocalDateTime lastLoginDate;
    private Integer loginCount;

}
