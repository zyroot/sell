package com.eim.service.impl;

import com.eim.pojo.ProductCategory;
import com.eim.service.ProductCategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Zy on 2018/10/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void findOne() throws Exception {
        ProductCategory one =productCategoryService.findOne(2);
        Assert.assertEquals(new Integer(2),one.getCategoryId());
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> all = productCategoryService.findAll();
        Assert.assertNotEquals(0,all.size());
    }

    @Test
    public void findBycategoryTypeIn() throws Exception {
        List<ProductCategory> bycategoryTypeIn = productCategoryService.findBycategoryTypeIn(Arrays.asList(1,4,5));
//        Assert.assertNotEquals(0,bycategoryTypeIn.size());
        System.out.println(bycategoryTypeIn);
    }

    @Test
    public void save() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("无敌毫克");
        productCategory.setCategoryType(55);
        ProductCategory save = productCategoryService.save(productCategory);
        Assert.assertNotNull(save);
    }


}