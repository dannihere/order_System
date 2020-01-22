package com.yu.service.impl;

import com.SellApplication;
import com.yu.dataobject.SellerInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SellApplication.class)
@Slf4j
public class SellerServiceImplTest {

    private static final String openid = "abc";


    @Autowired
    private SellerServiceImpl sellerService;

    @Test
    public void findSellerInfoByOpenid() {

        SellerInfo result = sellerService.findSellerInfoByOpenid(openid);
        Assert.assertEquals(openid,result.getOpenid());
    }
}