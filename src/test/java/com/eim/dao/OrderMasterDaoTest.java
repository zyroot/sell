package com.eim.dao;

import com.eim.pojo.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by Zy on 2018/10/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Test
    public void save() throws Exception {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("sssss");
        orderMaster.setBuyerName("小向");
        orderMaster.setBuyerPhone("12345678912");
        orderMaster.setBuyerAddress("地址");
        orderMaster.setBuyerOpenid("110");
        orderMaster.setOrderAmount(new BigDecimal(2000));
        OrderMaster result = orderMasterDao.save(orderMaster);

        Assert.assertNotNull(result);

    }

    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest request = new PageRequest(1,1);
        Page<OrderMaster> byBuyerOpenid = orderMasterDao.findByBuyerOpenid("110", request);
        System.out.println("总个数："+byBuyerOpenid.getTotalElements());
        System.out.println("总页数："+byBuyerOpenid.getTotalPages());
    }

}