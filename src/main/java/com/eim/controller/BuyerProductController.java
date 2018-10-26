package com.eim.controller;

import com.eim.pojo.ProductCategory;
import com.eim.pojo.ProductInfo;
import com.eim.service.ProductCategoryService;
import com.eim.service.impl.ProductInfoServiceImpl;
import com.eim.voPojo.ProductInfoVo;
import com.eim.voPojo.ProductVo;
import com.eim.voPojo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zy on 2018/10/26.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVo list(){
        //1.查询所有的上家商品
        List<ProductInfo> upAll = productInfoService.findUpAll();
        //2.查询类目
        List<Integer> list = new ArrayList<>();
        //传统方法
//        for (ProductInfo productInfo:upAll){
//            list.add(productInfo.getCategoryType());
//        }
        //经典方法（lambda语法8.0）
        List<Integer> listin = upAll.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> bycategoryTypeIn = productCategoryService.findBycategoryTypeIn(listin);

        //3.数据拼装
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("成功");

        ProductVo productVo = new ProductVo();
        ProductInfoVo productInfoVo = new ProductInfoVo();
        productVo.setProductInfoVoList(Arrays.asList(productInfoVo));

        resultVo.setData(Arrays.asList(productVo));
        return resultVo;
    }
}
