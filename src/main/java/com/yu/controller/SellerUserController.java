package com.yu.controller;


import com.yu.config.ProjectUrlConfig;
import com.yu.constant.CookieConstant;
import com.yu.constant.RedisConstant;
import com.yu.dataobject.SellerInfo;
import com.yu.enums.ResultEnum;
import com.yu.service.SellerService;
import com.yu.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    //redis\String
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String, Object> map) {

        //1、openid去和数据库里的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (sellerInfo == null) {
            map.put("msg", ResultEnum.LOGIN_FAIL.getMessage());
            return new ModelAndView("common/error");
        }

        //2、设置token至redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;

        //key/value/过期时间/时间格式秒
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid, expire, TimeUnit.SECONDS);

        //3、设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);
        return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/sell/seller/order/list");

    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String,Object> map) {

        //1、从cookie里查询
        Cookie cookie = CookieUtil.get(request,CookieConstant.TOKEN);
        if (cookie !=null){
            //2、清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getName()));

            //3、清除cookie 直接将过期时间设置为0
            CookieUtil.set(response,CookieConstant.TOKEN,null,0);
        }

        map.put("msg",ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("common/success",map);


    }
}
