package com.eim.service.impl;


import com.eim.dao.ProductInfoDao;
import com.eim.dto.CarDto;
import com.eim.enums.ProductStatusEnum;
import com.eim.enums.ResultEnum;
import com.eim.exception.SellException;
import com.eim.pojo.ProductInfo;
import com.eim.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;
import java.util.List;

/**
 * Created by Zy on 2018/10/26.
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService{


    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoDao.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatusIn(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {//分页查询对象
        return productInfoDao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDao.save(productInfo);
    }

    /**
     * 减库存
     * @param carDtoList
     */
    @Override
    @Transactional
    public void decreaseStock(List<CarDto> carDtoList) {
        for (CarDto carDto:carDtoList){//遍历购物车集合
            ProductInfo productInfo = productInfoDao.findOne(carDto.getProductId());//根据id  查询出商品
            if(productInfo == null){//非空判断
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //商品库存 - 购物车数量
            Integer productStock = productInfo.getProductStock() - carDto.getProductQuantity();
            if(productStock < 0){
                throw  new SellException(ResultEnum.product_stock_error);
            }
            productInfo.setProductStock(productStock);//设置库存
            productInfoDao.save(productInfo);
        }
    }

    @Override
    public void increaseStock(List<CarDto> carDtoList) {

    }
}
