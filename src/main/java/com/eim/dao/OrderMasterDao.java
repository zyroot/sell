package com.eim.dao;

import com.eim.pojo.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Zy on 2018/10/29.
 */
public interface OrderMasterDao extends JpaRepository<OrderMaster,String>{

    /**按照买家openid查询  并且分页*/
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
