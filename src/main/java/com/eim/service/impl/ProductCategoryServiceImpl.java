package com.eim.service.impl;

import com.eim.dao.ProductCategoryDao;
import com.eim.pojo.ProductCategory;
import com.eim.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类目
 * Created by Zy on 2018/10/26.
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{

    @Autowired
    private ProductCategoryDao productCategoryDao;

    /**
     * 根据id查询 productCategory
     * @param id
     * @return
     */
    @Override
    public ProductCategory findOne(Integer id) {
        return productCategoryDao.findOne(id);
    }

    /**
     * 查询所有productCategory
     * @return
     */
    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDao.findAll();
    }

    /**
     * 根据type list 查询 productCategory对象 list
     * @param list
     * @return
     */
    @Override
    public List<ProductCategory> findBycategoryTypeIn(List<Integer> list) {
        return productCategoryDao.findBycategoryTypeIn(list);
    }

    /**
     * 保存对象
     * @param productCategory
     * @return
     */
    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDao.save(productCategory);
    }
}
