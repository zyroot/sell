package com.eim.service;

import com.eim.pojo.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类目Service
 * Created by Zy on 2018/10/26.
 */
public interface ProductCategoryService {

      ProductCategory findOne(Integer id);

      List<ProductCategory> findAll();

      /**根据类目类型查找*/
      List<ProductCategory>  findBycategoryTypeIn(List<Integer> list);

      ProductCategory save(ProductCategory productCategory);

}
