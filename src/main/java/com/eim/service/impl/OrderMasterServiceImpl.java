package com.eim.service.impl;

import com.eim.convert.OrderMaster2OrderDto;
import com.eim.dao.OrderDetailDao;
import com.eim.dao.OrderMasterDao;
import com.eim.dto.CarDto;
import com.eim.dto.OrderDto;
import com.eim.enums.OrderEnum;
import com.eim.enums.PayStatusEnum;
import com.eim.enums.ResultEnum;
import com.eim.exception.SellException;
import com.eim.pojo.OrderDetail;
import com.eim.pojo.OrderMaster;
import com.eim.pojo.ProductInfo;
import com.eim.service.OrderMasterService;
import com.eim.service.ProductInfoService;
import com.eim.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Zy on 2018/10/29.
 */
@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService {


    /**
     * 商品详情
     */
    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 订单详细
     */
    @Autowired
    private OrderDetailDao orderDetailDao;

    /**
     * 主订单
     */
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
            orderAmount = productInfo.getProductPrice()//价格一定要查数据库获取（安全）
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);//对象属性拷贝 id name price
            orderDetailDao.save(orderDetail);
        }

        //3，写入订单数据库（orderMaster,orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderId(orderId);//设置id
        BeanUtils.copyProperties(orderDto,orderMaster);//注意一定是先拷贝再设置
        orderMaster.setOrderAmount(orderAmount);//设置总价格
        orderMaster.setOrderStatus(OrderEnum.NEW.getCode());//因为属性拷贝，订单状态和支付状态需要收到设置
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

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
        OrderMaster orderMaster = orderMasterDao.findOne(orderId);
        if(orderMaster == null){
            throw  new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if(orderDetailList == null){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        orderDto.setOrderDetails(orderDetailList);
        return orderDto;
    }

    /**
     * 查询订单列表
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> page = orderMasterDao.findByBuyerOpenid(buyerOpenid,pageable);
        List<OrderDto> orderDtoList = OrderMaster2OrderDto.convert(page.getContent());
        Page<OrderDto> orderDtos = new PageImpl<OrderDto>(orderDtoList,pageable,page.getTotalElements());
        return orderDtos;
    }

    /**
     * 取消订单
     * @param orderDto
     * @return
     */
    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if(!orderDto.getOrderStatus().equals(OrderEnum.NEW.getCode())){
            log.error("【取消订单】订单状态不正确，orderId={},roderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDto.setOrderStatus(OrderEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster result = orderMasterDao.save(orderMaster);
        if(result == null){
            log.error("【取消订单】更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        //返回库存
        if(CollectionUtils.isEmpty(orderDto.getOrderDetails())){
            log.error("【取消订单】订单中无商品详情，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CarDto> carDtoList = orderDto.getOrderDetails().stream().map(e ->
                new CarDto(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.increaseStock(carDtoList);
        //如果已支付 退款
        if(orderDto.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
                //TODO
        }
        return orderDto;
    }

    /**
     * 完结订单
     * @param orderDto
     * @return
     */
    @Override
    public OrderDto finish(OrderDto orderDto) {
        //判断订单状态
        if(!orderDto.getOrderStatus().equals(OrderEnum.NEW.getCode())){
            log.error("【完结订单】订单状态不正确，orderId={},orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw  new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDto.setOrderStatus(OrderEnum.FINISH.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);

        OrderMaster save = orderMasterDao.save(orderMaster);
        if(save == null){
            log.error("【完结订单】更新失败 orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }

        return orderDto;
    }

    /**
     * 支付订单
     * @param orderDto
     * @return
     */
    @Override
    public OrderDto paid(OrderDto orderDto) {
        //判断订单状态
        if(!orderDto.getOrderStatus().equals(OrderEnum.NEW.getCode())){
            log.error("【订单支付完成】 订单状态不正确 orderId={},orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if(!orderDto.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("【订单支付完成】 订单支付状态不正确 orderId={},payStatus={}",orderDto.getOrderId(),orderDto.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);

        OrderMaster save = orderMasterDao.save(orderMaster);
        if(save == null){
            log.error("【订单支付完成】更新失败 orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }
        return orderDto;
    }
}
