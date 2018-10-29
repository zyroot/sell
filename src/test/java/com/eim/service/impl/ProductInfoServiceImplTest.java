package com.eim.service.impl;

import com.eim.enums.ProductStatusEnum;
import com.eim.pojo.ProductInfo;
import com.eim.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Zy on 2018/10/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductInfoServiceImplTest {
    @Autowired
    private ProductInfoService productInfoService;
    @Test
    public void findOne() throws Exception {
        ProductInfo one = productInfoService.findOne("345");
        Assert.assertEquals("345",one.getProductId());
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> upAll = productInfoService.findUpAll();
        System.out.println(upAll);
        Assert.assertNotEquals(0,upAll.size());
    }

    @Test
    public void findAll() throws Exception {
        PageRequest pageRequest = new PageRequest(1,2);
        Page<ProductInfo> all = productInfoService.findAll(pageRequest);
        long totalElements = all.getTotalElements();
        System.out.println(totalElements);
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("445");
        productInfo.setProductName("小苹果");
        productInfo.setProductPrice(new BigDecimal(88.0));
        productInfo.setProductStock(2);
        productInfo.setProductDescription("最好吃的苹果");
        productInfo.setProductIcon("http://ssss.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(5);
        ProductInfo save = productInfoService.save(productInfo);
        Assert.assertNotNull(save);
    }

}