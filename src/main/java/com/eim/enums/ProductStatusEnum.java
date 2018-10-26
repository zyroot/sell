package com.eim.enums;

import lombok.Data;
import lombok.Getter;

/**
 * Created by Zy on 2018/10/26.
 */
@Getter
public enum  ProductStatusEnum {

    UP(0,"在架"),
    DOWN(1,"下架"),
    ;

    private  Integer code;

    private  String message;

    ProductStatusEnum() {
    }

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
