package com.eim.service;

import com.eim.dto.CarDto;
import com.eim.pojo.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Zy on 2018/10/26.
 */
public interface ProductInfoService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有在架的商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //减库存
    void decreaseStock(List<CarDto> carDtoList);

    //加库存
    void increaseStock(List<CarDto> carDtoList);



}
