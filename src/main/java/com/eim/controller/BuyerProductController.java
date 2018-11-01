package com.eim.controller;

import com.eim.pojo.ProductCategory;
import com.eim.pojo.ProductInfo;
import com.eim.service.ProductCategoryService;
import com.eim.service.ProductInfoService;
import com.eim.utils.ResultVoUtil;
import com.eim.voPojo.ProductInfoVo;
import com.eim.voPojo.ProductVo;
import com.eim.voPojo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zy on 2018/10/26.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired//商品
    private ProductInfoService productInfoService;

    @Autowired//类目
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVo list(){
        //1.查询所有的上架商品
        List<ProductInfo> upAll = productInfoService.findUpAll();
        //2.查询类目 一次性查询
        List<Integer> typeList = new ArrayList<>();
        //传统方法  根据上架商品查询出类别  添加到集合中，再查询数据库 返回类目集合
        for (ProductInfo productInfo:upAll){
            typeList.add(productInfo.getCategoryType());
        }
        List<ProductCategory> productCategoryList = productCategoryService.findBycategoryTypeIn(typeList);

        //经典方法（lambda语法8.0）
//        List<Integer> listin = upAll.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
//        List<ProductCategory> productCategoryList = productCategoryService.findBycategoryTypeIn(listin);
        //3.数据拼装
        List<ProductVo> productVoList = new ArrayList<>();
        for(ProductCategory productCategory: productCategoryList){
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo:upAll){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo,productInfoVo);//Spring提供的工具类，复制对应属性
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }

        return ResultVoUtil.success(productVoList);
    }
}
