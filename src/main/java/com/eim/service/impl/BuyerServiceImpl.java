package com.eim.service.impl;

import com.eim.dto.OrderDto;
import com.eim.enums.ResultEnum;
import com.eim.exception.SellException;
import com.eim.service.BuyerService;
import com.eim.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Zy on 2018/11/1.
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderMasterService orderMasterService;

    @Override
    public OrderDto findOrderOne(String openId, String orderId) {
        OrderDto orderDto = checkOrderOwner(openId, orderId);
        return  orderDto;
    }

    @Override
    public OrderDto cancelOrder(String openId, String orderId) {
        OrderDto orderDto = checkOrderOwner(openId, orderId);
        if(orderDto == null){
            log.error("【取消订单】查不到该订单 orderDto={}",orderDto);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        OrderDto cancel = orderMasterService.cancel(orderDto);
        return cancel;
    }

    private OrderDto checkOrderOwner(String openId, String orderId){
        OrderDto one = orderMasterService.findOne(orderId);
        if(one == null){
            return  null;
        }
        //判断是否是自己的订单
        if(!one.getBuyerOpenid().equals(openId)){
            log.error("【查询订单】 订单的openid 不一致 ，openId={},orderDto={}",openId,one);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return one;
    }
}
