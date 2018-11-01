package com.eim.convert;

import com.eim.dto.OrderDto;
import com.eim.enums.ResultEnum;
import com.eim.exception.SellException;
import com.eim.form.OrderForm;
import com.eim.pojo.OrderDetail;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by Zy on 2018/11/1.
 */
@Slf4j
public class OrderForm2OrderDtoConverter {

    public   static OrderDto convert(OrderForm orderForm){
        Gson gson = new Gson();

        OrderDto orderDto = new OrderDto();

        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerOpenid(orderForm.getOpenid());
        orderDto.setBuyerAddress(orderForm.getAddress());
        //购物车字符转称list集合
        List<OrderDetail> orderDetailList = null;
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                          new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (JsonSyntaxException e) {
            log.error("【对象转换】错误，string={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDto.setOrderDetails(orderDetailList);
        return orderDto;
    }
}
