package com.eim.service;

import com.eim.dto.OrderDto;

/**
 * Created by Zy on 2018/11/1.
 */
public interface BuyerService {

    /**
     * 查询一个订单
     */
    OrderDto findOrderOne(String openId,String orderId);

    /**
     * 取消订单
     */
    OrderDto cancelOrder(String openId,String orderId);
}
