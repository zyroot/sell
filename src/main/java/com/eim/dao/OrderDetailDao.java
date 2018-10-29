package com.eim.dao;

import com.eim.pojo.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Zy on 2018/10/29.
 */
public interface OrderDetailDao extends JpaRepository<OrderDetail,String> {

    /**根据订单id查询订单详情列表*/
    List<OrderDetail> findByOrderId(String orderId);
}
