package com.yu.service;

import com.yu.dto.OrderDTO;

public interface BuyerService {

    //查询订单
    OrderDTO findOrderOne(String openid,String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid,String orderId);

}