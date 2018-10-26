package com.eim.pojo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 表的映射
 * 属性最好使用驼峰命名法
 * Created by Zy on 2018/10/25.
 */
@Table(name = "product_category")//指定表明
@DynamicUpdate//自动更新时间
@Entity//实体类注解
@Data//省略写 get set 和 tostring
public class ProductCategory{
    /**
     * CREATE TABLE `product_category` (
     `category_id` int(11) NOT NULL AUTO_INCREMENT,
     `category_name` varchar(64) NOT NULL COMMENT '类目名称',
     `category_type` int(11) NOT NULL COMMENT '类目编号',
     `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     PRIMARY KEY (`category_id`),
     UNIQUE KEY `uqe_category_type` (`category_type`)
     ) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='类目表';
     */
    /** 类目id */
    @Id//主键标识
    @GeneratedValue//自增标识
    private Integer categoryId;
    /** 类目名字*/
    private String categoryName;
    /** 类目类型*/
    private Integer categoryType;


}
