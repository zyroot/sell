package com.eim.voPojo;

import lombok.Data;

/**
 * http请求 返回的最外层对象
 * Created by Zy on 2018/10/26.
 */
@Data
public class ResultVo {

    /** 错误码 */
    private Integer code;
    /** 具体内容*/
    private String msg;
    /** 提示信息*/
    private Object  data;
}
