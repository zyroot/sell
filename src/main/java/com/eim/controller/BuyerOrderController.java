package com.eim.controller;

import com.eim.convert.OrderForm2OrderDtoConverter;
import com.eim.dto.OrderDto;
import com.eim.enums.ResultEnum;
import com.eim.exception.SellException;
import com.eim.form.OrderForm;
import com.eim.service.BuyerService;
import com.eim.service.OrderMasterService;
import com.eim.utils.ResultVoUtil;
import com.eim.voPojo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zy on 2018/11/1.
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVo<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建表单】参数不正确,orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                                     bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = OrderForm2OrderDtoConverter.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDto.getOrderDetails())){
            log.error("【创建订单】 购物车为空 orderDetailList={}",orderDto.getOrderDetails());
            throw new SellException(ResultEnum.CAR_EMPTY);
        }
        orderMasterService.createOrder(orderDto);

        Map<String,String> map = new HashMap<>();
        map.put("orderId",orderDto.getOrderId());
        return ResultVoUtil.success(map);
    }

    //订单列表
    @RequestMapping("orderList")
    public ResultVo<List<OrderDto>> list(@RequestParam("openid") String openid,
                                          @RequestParam(value = "page",defaultValue = "0")Integer page,
                                          @RequestParam(value = "size",defaultValue = "10")Integer size){
            if(StringUtils.isEmpty(openid)){
                log.error("【订单列表】 openid={}",openid);
                throw new SellException(ResultEnum.PARAM_ERROR);
            }

        PageRequest request = new PageRequest(page, size);
        Page<OrderDto> list = orderMasterService.findList(openid, request);

        return  ResultVoUtil.success(list);
    }

    //订单详情
    @RequestMapping("/detail")
    public ResultVo<OrderDto> detail(@RequestParam("openId") String openid,
                                      @RequestParam("orderId")String orderId){
        //TODO 不安全的做法
        OrderDto orderOne = buyerService.findOrderOne(openid, orderId);
        return ResultVoUtil.success(orderOne);
    }

    //取消订单
    @RequestMapping("/cancel")
    public ResultVo<OrderDto> cancel(@RequestParam("openId") String openid,
                                     @RequestParam("orderId")String orderId){
        buyerService.cancelOrder(openid, orderId);
        return ResultVoUtil.success(null);
    }

}
