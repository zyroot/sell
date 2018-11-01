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

import java.util.List;

/**
 * Created by Zy on 2018/10/26.
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService{

    /***
     * 注入productInfoDao
     */
    @Autowired
    private ProductInfoDao productInfoDao;

    /**
     * 根据id 查询productInfo
     * @param productId
     * @return
     */
    @Override
    public ProductInfo findOne(String productId) {
        return productInfoDao.findOne(productId);
    }

    /**
     * 查询所有上架的商品
     * @return
     */
    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatusIn(ProductStatusEnum.UP.getCode());//使用枚举 防止硬编码问题
    }

    /**
     * 分页查询productInfo
     * @param pageable 分页对象
     * @return Page（接口）
     */
    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {//分页查询对象
        return productInfoDao.findAll(pageable);
    }

    /**
     * 保存和更新
     * @param productInfo
     * @return
     */
    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDao.save(productInfo);
    }

    /**
     * 减库存
     * @param carDtoList 购物车集合，前端传数的数据
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
                throw  new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(productStock);//设置库存
            productInfoDao.save(productInfo);
        }
    }

    /**
     * 增加库存
     * @param carDtoList
     */
    @Override
    public void increaseStock(List<CarDto> carDtoList) {
        for (CarDto carDto : carDtoList) {
            ProductInfo productInfo = productInfoDao.findOne(carDto.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer productStock = productInfo.getProductStock() + carDto.getProductQuantity();
            productInfo.setProductStock(productStock);
            productInfoDao.save(productInfo);
        }

    }
}
