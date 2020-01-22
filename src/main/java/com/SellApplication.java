package com;

import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@Import(BestPayServiceImpl.class)
public class SellApplication {

    public static void main(String[] args) {
        SpringApplication.run(SellApplication.class, args);
    }
}
