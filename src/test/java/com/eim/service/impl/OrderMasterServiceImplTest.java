package com.eim.service.impl;

import com.eim.dto.OrderDto;
import com.eim.pojo.OrderDetail;
import com.eim.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zy on 2018/10/31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterServiceImplTest {

    @Autowired
    private OrderMasterService orderMasterService;

    @Test
    public void createOrder() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("孙悟空33");
        orderDto.setBuyerAddress("东方广场");
        orderDto.setBuyerPhone("12345679999");
        orderDto.setBuyerOpenid("123456");

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("12121");
        o1.setProductQuantity(2);
        orderDetailList.add(o1);
        orderDto.setOrderDetails(orderDetailList);

        OrderDto order = orderMasterService.createOrder(orderDto);
        log.info("创建订单 order={}",order);
        Assert.assertNotNull(order);
    }

    @Test
    public void findOne() throws Exception {
        String orderId = "1540953618385643422";
        OrderDto one = orderMasterService.findOne(orderId);
        log.info("【查询单个订单】result={}",one);
        Assert.assertNotNull(one);
    }

    @Test
    public void findList() throws Exception {
        String buyerId = "123456";
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderDto> page = orderMasterService.findList(buyerId, pageRequest);
        Assert.assertNotEquals(0,page.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
    }

    @Test
    public void finish() throws Exception {
    }

    @Test
    public void paid() throws Exception {
    }

}