package com.eim.dto;

import com.eim.enums.OrderEnum;
import com.eim.enums.PayStatusEnum;
import com.eim.pojo.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Zy on 2018/10/29.
 */
@Data
public class OrderDto {

    /**订单id*/
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
    private Integer orderStatus ;
    /**支付状态 默认为未支付*/
    private Integer payStatus;

    private Date createTime;

    private Date updateTime;

    List<OrderDetail> orderDetails ;
}
