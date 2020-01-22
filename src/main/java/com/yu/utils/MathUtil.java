package com.yu.utils;

public class MathUtil {

    private final static Double MONEY_RANG=0.01;


    /*
    * 比较两个金额是否相等
    * */
    public static Boolean equals(Double d1,Double d2){
        Double result = Math.abs(d1-d2);
        if (result<MONEY_RANG){
            return true;
        }
        return false;

    }
}
