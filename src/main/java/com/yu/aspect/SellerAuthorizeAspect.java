package com.yu.aspect;

import com.mysql.cj.util.StringUtils;
import com.yu.constant.CookieConstant;
import com.yu.constant.RedisConstant;
import com.yu.exception.SellerAuthorizeException;
import com.yu.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    //切点
    @Pointcut("execution(public * com.yu.controller.Seller*.*(..))"+
    "&& !execution(public * com.yu.controller.SellerUserController.*(..))")
    public void verify(){

    }

    @Before("verify()")
    public void doVerify(){
        //获取request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null){
            log.warn("【登录校验】Cookie中查不到token");
            throw new SellerAuthorizeException();
        }

        //去redis查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
        if (StringUtils.isNullOrEmpty(tokenValue)){
            log.warn("【登录校验】Redis中查不到token");
            throw new SellerAuthorizeException();
        }

    }

}
