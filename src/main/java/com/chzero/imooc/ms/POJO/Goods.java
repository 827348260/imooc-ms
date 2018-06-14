package com.chzero.imooc.ms.POJO;

import lombok.Data;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-13 13:41
 * @email 827348260@qq.com
 * @description
 */
@Data
public class Goods{

    private Long id;
    private String goodsName;
    private String goodsTitle;
    private String goodsImg;
    private String goodsDetail;
    private Double goodsPrice;
    private Integer goodsStock;

}
