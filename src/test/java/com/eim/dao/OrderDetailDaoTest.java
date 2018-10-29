package com.eim.dao;

import com.eim.pojo.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Zy on 2018/10/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> sssss = orderDetailDao.findByOrderId("555");
        Assert.assertNotNull(sssss);
    }

    @Test
    public void save() throws Exception {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123");
        orderDetail.setOrderId("555");
        orderDetail.setProductId("商品id");
        orderDetail.setProductIcon("http://ll.jpg");
        orderDetail.setProductId("ddd");
        orderDetail.setProductName("民资");
        orderDetail.setProductQuantity(10);
        orderDetail.setProductPrice(new BigDecimal(88.0));
        OrderDetail save = orderDetailDao.save(orderDetail);
        Assert.assertNotNull(orderDetail);
    }

}