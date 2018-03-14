package com.ants.ordermeal.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {

    private final  static Logger  logger = LoggerFactory.getLogger(HttpAspect.class);
    @Pointcut("execution(public * com.ants.ordermeal.controller.*.*(..))")
    public void log(){

    }
    @Before("log()")
    public  void doBefore(JoinPoint joinPoint){
        logger.info("-------Start Controller Method-------");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        //url
        logger.info("url={}",request.getRequestURI());
        //method
        logger.info("method={}",request.getMethod());
        //ip
        logger.info("ip={}",request.getRemoteAddr());
        //类方法
        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+" ."+joinPoint.getSignature().getName() );
        //参数
        logger.info("args", joinPoint.getArgs().toString());
    }
    @After("log()")
    public  void doAfter(){
        logger.info("--------End Controller Method-------");
    }

    //获取返回内容aop处理
    @AfterReturning(returning = "object",pointcut="log()")
    public void doAfterReturning(Object object){
        logger.info("response={}",object.toString());
    }
}
