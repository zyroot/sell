package com.eim.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 商品
 * Created by Zy on 2018/10/26.
 */
@Entity
@Data
public class ProductInfo {
    /**
     * CREATE TABLE `product_info` (
     `product_id` varchar(32) NOT NULL,
     `product_name` varchar(64) NOT NULL COMMENT '商品名称',
     `product_price` decimal(8,2) NOT NULL COMMENT '单价',
     `product_stock` int(11) NOT NULL COMMENT '库存',
     `product_description` varchar(64) DEFAULT NULL COMMENT '描述',
     `product_icon` varchar(512) DEFAULT NULL COMMENT '小图',
     `category_type` int(11) NOT NULL COMMENT '类目编号',
     `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     PRIMARY KEY (`product_id`)
     ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
     */
    @Id
    private  String productId;
    /**名字*/
    private  String productName;
    /**单价*/
    private BigDecimal productPrice;
    /**库存*/
    private  Integer productStock;
    /**描述*/
    private  String productDescription;
    /**小图*/
    private  String productIcon;
    /**商品状态 0 正常 1下架*/
    private  Integer productStatus;
    /**类目类型*/
    private  Integer categoryType;
}
