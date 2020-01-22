package com.yu.service;

import com.yu.dataobject.SellerInfo;

/**
 * 卖家端
 */
public interface SellerService {

    /**
     * 通过openid查询
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);
}
