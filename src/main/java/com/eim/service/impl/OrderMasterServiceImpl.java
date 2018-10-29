package com.eim.service.impl;

import com.eim.dao.OrderDetailDao;
import com.eim.dao.OrderMasterDao;
import com.eim.dto.CarDto;
import com.eim.dto.OrderDto;
import com.eim.enums.ResultEnum;
import com.eim.exception.SellException;
import com.eim.pojo.OrderDetail;
import com.eim.pojo.OrderMaster;
import com.eim.pojo.ProductInfo;
import com.eim.service.OrderMasterService;
import com.eim.service.ProductCategoryService;
import com.eim.service.ProductInfoService;
import com.eim.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zy on 2018/10/29.
 */
@Service
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;


    @Override
    @Transactional//添加事务
    public OrderDto createOrder(OrderDto orderDto) {
        /**订单总价 */
        String orderId = KeyUtil.getUniqueKey();
        BigDecimal  orderAmount = new BigDecimal(BigInteger.ZERO);
        //1,查询商品（数量，价格）
        List<OrderDetail> orderDetails = orderDto.getOrderDetails();
        for(OrderDetail orderDetail:orderDetails){
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算订单总价  bigdecimal乘法  价格*数量+总价
            orderAmount = orderDetail.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);//对象属性拷贝
            orderDetailDao.save(orderDetail);
        }

        //3，写入订单数据库（orderMaster,orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderDto,orderMaster);

        orderMasterDao.save(orderMaster);
        //4,扣库存
        List<CarDto> carDtoList = orderDto.getOrderDetails().stream().map(e ->
                new CarDto(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.decreaseStock(carDtoList);
        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDto cancel(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto paid(OrderDto orderDto) {
        return null;
    }
}
