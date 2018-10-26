package com.eim.dao;

import com.eim.pojo.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Zy on 2018/10/26.
 */
public interface ProductInfoDao extends JpaRepository<ProductInfo,String>{
    List<ProductInfo> findByProductStatusIn(Integer productStatus );
}
