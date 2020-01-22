package com.yu.service.impl;

import com.yu.dto.OrderDTO;
import com.yu.enums.ResultEnum;
import com.yu.exception.SellException;
import com.yu.service.BuyerService;
import com.yu.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            log.error("【取消订单】找不到订单，orderid={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }
        return orderService.cancel(orderDTO);

    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【查询订单】订单的openid不一样，openid={}，orderDTO={}", openid, orderDTO);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        return orderDTO;
    }


}
