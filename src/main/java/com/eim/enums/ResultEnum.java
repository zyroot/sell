package com.eim.enums;

import lombok.Getter;

/**
 * Created by Zy on 2018/10/29.
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    product_stock_error(11,"库存不正确")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}