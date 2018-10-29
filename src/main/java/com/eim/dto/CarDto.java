package com.eim.dto;

import lombok.Data;

/**
 * 购物车
 * Created by Zy on 2018/10/29.
 */
@Data
public class CarDto {

    /**
     * 商品id
     */
    private String productId;

    /**
     * 数量
     */
    private Integer productQuantity;

    public CarDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
