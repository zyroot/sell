package com.eim.utils;

import java.util.Random;

/**
 * Created by Zy on 2018/10/29.
 */
public class KeyUtil {

    /**
     *  生成唯一的主键
     *  格式： 时间+随机数
     * */
    public static synchronized String getUniqueKey(){//同步锁
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return  System.currentTimeMillis() + String.valueOf(number);
    }
}
