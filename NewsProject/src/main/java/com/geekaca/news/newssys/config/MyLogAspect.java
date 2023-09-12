package com.geekaca.news.newssys.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

@Component
@Aspect
@Slf4j
public class MyLogAspect {
    @Pointcut("@annotation(com.geekaca.news.newssys.config.MyLog)")
    public void myLogAspect(){}

    @Before("myLogAspect()")
    public void beforeMyLog(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String methodName = joinPoint.getSignature().getName();
        System.out.println("===================method" + methodName + "start===============");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date();
        String time = sdf.format(d);
        System.out.println("Time       :" + time);
        System.out.println("Url        :" + request.getRequestURL());
        System.out.println("HTTP Method:" + joinPoint.getSignature().getDeclaringTypeName() + "." + ((MethodSignature) joinPoint.getSignature()).getName());
        System.out.println("IP         :" + request.getRemoteHost());
        System.out.println("========================================================");
    }

    @After("myLogAspect()")
    public void afterMyLog(JoinPoint joinPoint) {String methodName=joinPoint.getSignature().getName();
    log.info("=======================Method"+methodName+"end=====================");}
}
