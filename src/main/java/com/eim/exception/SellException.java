package com.eim.exception;

import com.eim.enums.ResultEnum;

/**
 * Created by Zy on 2018/10/29.
 */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
