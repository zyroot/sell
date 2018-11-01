package com.eim.convert;

import com.eim.dto.OrderDto;
import com.eim.pojo.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zy on 2018/10/31.
 */
public class OrderMaster2OrderDto {

    public static OrderDto convert(OrderMaster orderMaster){
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        return orderDto;
    }
    public static List<OrderDto> convert(List<OrderMaster> orderMasterList){
        List<OrderDto> collect = orderMasterList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
        return collect;
    }


}
