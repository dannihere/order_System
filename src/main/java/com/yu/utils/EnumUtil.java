package com.yu.utils;

import com.yu.enums.CodeEnum;


/**
 * 通过code获取msg
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code,Class<T> enumClass){
        for (T each: enumClass.getEnumConstants()){
            if (code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
