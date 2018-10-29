package com.eim.pojo;

import com.eim.enums.OrderEnum;
import com.eim.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Zy on 2018/10/29.
 */
@Data
@Entity
@DynamicUpdate
public class OrderMaster {
//
//CREATE TABLE `order_master` (
//`order_id` varchar(32) NOT NULL,
//`buyer_name` varchar(32) NOT NULL COMMENT '买家名字',
//`buyer_phone` varchar(32) NOT NULL COMMENT '买家电话',
//`buyer_address` varchar(128) NOT NULL COMMENT '买家地址',
//`buyer_openid` varchar(64) NOT NULL COMMENT '买家微信openid',
//`order_amount` decimal(8,2) NOT NULL COMMENT '订单总额',
//`order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态,默认0下单',
//`pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态，默认0未支付',
//PRIMARY KEY (`order_id`),
//KEY `idx_buyer_openid` (`buyer_openid`)
//) ENGINE=InnoDB DEFAULT CHARSET=latin1;
    /**订单id*/
    @Id
    private String orderId;
    /**买家名字*/
    private String buyerName;
    /**买家电话*/
    private String buyerPhone;
    /**买家地址*/
    private String buyerAddress;
    /**买家微信openid*/
    private String buyerOpenid;
    /**点单金额*/
    private BigDecimal orderAmount;
    /**点单状态 默认为新下单*/
    private Integer orderStatus = OrderEnum.NEW.getCode();
    /**支付状态 默认为未支付*/
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;
}
