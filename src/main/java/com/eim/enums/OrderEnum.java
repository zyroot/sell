package com.eim.enums;

import lombok.Getter;

/**
 * Created by Zy on 2018/10/29.
 */
@Getter
public enum OrderEnum {

    NEW(0,"新订单"),
    FINISH(1,"完成"),
    CANCEL(2,"已取消")
    ;

    private Integer code;

    private String message;

    OrderEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
