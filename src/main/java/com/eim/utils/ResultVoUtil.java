package com.eim.utils;

import com.eim.voPojo.ResultVo;

/**
 * Created by Zy on 2018/10/29.
 */
public class ResultVoUtil {

    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setData(object);
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        return  resultVo;
    }

}
