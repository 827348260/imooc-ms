package com.chzero.imooc.ms.POJO;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-13 13:44
 * @email 827348260@qq.com
 * @description
 */
@Data
public class MSGoods{

    private Long id;
    private Long idGoods;
    private Integer stockCount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
