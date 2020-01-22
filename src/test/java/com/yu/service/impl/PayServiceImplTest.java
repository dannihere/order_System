package com.yu.service.impl;

import com.SellApplication;
import com.yu.dto.OrderDTO;
import com.yu.service.OrderService;
import com.yu.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SellApplication.class)
@Slf4j
public class PayServiceImplTest{

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;


    @Test
    public void create()throws Exception{
        OrderDTO orderDTO = orderService.findOne("1579073306590738549");
        payService.create(orderDTO);
    }

    @Test
    public void refund(){
        OrderDTO orderDTO = orderService.findOne("1579073306590738549");
        payService.refund(orderDTO);
    }


}
