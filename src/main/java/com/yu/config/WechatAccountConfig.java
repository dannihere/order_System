package com.yu.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    private String mpAppId;
    private String mpAppSecret;

    private String openAppId;
    private String openAppSecret;
    /*
    * 商户号
    * */
    private String mchId;
    /*s
    * 商户密钥
    * */
    private String mchKey;
    /*
    * 商户证书路径
    * */
    private String keyPath;
  //  private SSLContext sslContext;
    /*
    * 很重要，微信支付异步通知地址
    * */
    private String notifyUrl;

    /**
     * 微信模板ID
     */
    private Map<String,String> templateId;
}
