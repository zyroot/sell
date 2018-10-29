package com.eim.service;

import com.eim.dto.OrderDto;
import com.eim.pojo.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Zy on 2018/10/29.
 */
public interface OrderMasterService {

    /**创建订单*/
    OrderDto createOrder(OrderDto orderDto);
    /**查询单个订单*/
    OrderDto findOne(String orderId);
    /**查询订单列表*/
    Page<OrderDto> findList(String buyerOpenid, Pageable pageable);
    /**取消订单*/
    OrderDto cancel(OrderDto orderDto);
    /**完结订单*/
    OrderDto finish(OrderDto orderDto);
    /**支付订单*/
    OrderDto paid(OrderDto orderDto);
}
