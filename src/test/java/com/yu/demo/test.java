package com.yu.demo;

import java.math.BigDecimal;

public class test {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(3.2);
        BigDecimal b = new BigDecimal(999);
        a = b.multiply(new BigDecimal(2)).add(a);
        System.out.println(a);
    }
}
