package com.eim;

import com.eim.pojo.OrderDetail;
import com.eim.pojo.ProductInfo;
import com.eim.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Zy on 2018/10/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {


    @Test
    public void test1(){
            log.info("dddd");
    }

    @Test
    public void test(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(KeyUtil.getUniqueKey());
        productInfo.setProductName("小米加步枪");

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(KeyUtil.getUniqueKey());
        orderDetail.setOrderId(KeyUtil.getUniqueKey());

        BeanUtils.copyProperties(productInfo,orderDetail);
        log.info("dddd");
    }
}
