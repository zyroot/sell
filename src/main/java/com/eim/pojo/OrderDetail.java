package com.eim.pojo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情表
 * Created by Zy on 2018/10/29.
 */
@Entity
@Data
@DynamicUpdate
public class OrderDetail {
    /**
     * CREATE TABLE `order_detail` (
     `detail_id` varchar(32) NOT NULL,
     `order_id` varchar(32) NOT NULL,
     `product_id` varchar(32) NOT NULL,
     `product_name` varchar(64) NOT NULL,
     `product_price` decimal(8,2) NOT NULL,
     `product_quantity` int(11) NOT NULL COMMENT '商品数量',
     `product_icon` varchar(512) DEFAULT NULL COMMENT '商品小图',
     `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     PRIMARY KEY (`detail_id`),
     KEY `idx_order_id` (`order_id`)
     ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
     */
    /**详情id*/
    @Id
    private String detailId;
    /**订单id*/
    private String orderId;
    /**商品id*/
    private String productId;
    /**商品名字*/
    private String productName;
    /**商品价格*/
    private BigDecimal productPrice;
    /**商品数量*/
    private Integer productQuantity;
    /**商品图标*/
    private String productIcon;
    /**创建时间*/
    private Date createTime;
    /**修改时间*/
    private Date updateTime;
}
