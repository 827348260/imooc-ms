package com.chzero.imooc.ms.vo;

import com.chzero.imooc.ms.POJO.Goods;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-13 14:16
 * @email 827348260@qq.com
 * @description
 */
@Data
public class GoodsVo extends Goods{

    private Double msPrice;
    private Integer stockCount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
