package com.eim.dao;

import com.eim.pojo.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Zy on 2018/10/25.
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer>{

    List<ProductCategory> findBycategoryTypeIn(List<Integer> list);


}
