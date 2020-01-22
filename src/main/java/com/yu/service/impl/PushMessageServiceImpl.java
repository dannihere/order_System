package com.yu.service.impl;

import com.yu.config.WechatAccountConfig;
import com.yu.dto.OrderDTO;
import com.yu.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig accountConfig;

    @Override
    public void orderStatus(OrderDTO orderDTO) {

        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(accountConfig.getTemplateId().get("orderStatus"));//模板ID
        templateMessage.setToUser(orderDTO.getBuyerOpenid());//粉丝的openid

        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first","nice啊,亲"),
                new WxMpTemplateData("keyword1","微信点餐"),
                new WxMpTemplateData("keyword2","11111111111"),
                new WxMpTemplateData("keyword3",orderDTO.getOrderId()),
                new WxMpTemplateData("keyword4",orderDTO.getOrderStatusEnum().getMsg()),
                new WxMpTemplateData("keyword5","￥"+orderDTO.getOrderAmount()),
                new WxMpTemplateData("remark","欢迎再次光临")
        );

        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            //只记录日志，发生错误时不会使数据进行回滚
            log.error("【微信模板消息】发送失败，{}",e);
        }
    }
}
