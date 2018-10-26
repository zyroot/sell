package com.eim.dao;

import com.eim.pojo.ProductInfo;
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
 * Created by Zy on 2018/10/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void findByProductStatusIn(){
        List<ProductInfo> byProductStatusIn = productInfoDao.findByProductStatusIn(0);
        Assert.assertNotEquals(0,byProductStatusIn.size());
    }

    //
    @Test
    public void save(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("345");
        productInfo.setProductName("小苹果");
        productInfo.setProductPrice(new BigDecimal(88.0));
        productInfo.setProductStock(2);
        productInfo.setProductDescription("最好吃的苹果");
        productInfo.setProductIcon("http://ssss.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(4);
        ProductInfo save = productInfoDao.save(productInfo);
        Assert.assertNotNull(save);
//        Assert.assertNotEquals(0,byProductStatusIn.size());
    }
}