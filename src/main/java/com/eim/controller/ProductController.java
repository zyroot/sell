package com.eim.controller;

import com.eim.pojo.ProductInfo;
import com.eim.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Zy on 2018/10/26.
 */
@Controller
public class ProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @ResponseBody
    @RequestMapping("/getAll.do")
    public String getAllProduct(){
        List<ProductInfo> upAll = productInfoService.findUpAll();
        return  upAll.toString();
    }
}
