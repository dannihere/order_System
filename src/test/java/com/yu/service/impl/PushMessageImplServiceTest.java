package com.yu.service.impl;

import com.SellApplication;
import com.yu.dto.OrderDTO;
import com.yu.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SellApplication.class)
public class PushMessageImplServiceTest {

    @Autowired
    private PushMessageServiceImpl pushMessageService;

    @Autowired
    private OrderService orderService;

    @Test
    public void orderStatus() {

        OrderDTO orderDTO = orderService.findOne("1579350577143650086");
        pushMessageService.orderStatus(orderDTO);
    }
}