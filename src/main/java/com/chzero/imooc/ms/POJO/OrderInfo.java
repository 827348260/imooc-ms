package com.chzero.imooc.ms.POJO;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-13 13:54
 * @email 827348260@qq.com
 * @description
 */
@Data
public class OrderInfo{

    private Long id;
    private Long idUser;
    private Long idGoods;
    private Long idDeliveryAddr;
    private String goodsName;
    private Integer goodsCount;
    private Double goodsPrice;
    private Integer orderChannel;
    private Integer status;
    private LocalDateTime createDate;
    private LocalDateTime payDate;


}
