package com.wesley.prac.spring.demo.aspect;

import com.wesley.prac.spring.formework.aop.aspect.WJoinPiont;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Slf4j
public class LogAspect {

    private static final Logger log=LoggerFactory.getLogger(LogAspect.class);
    public void before(WJoinPiont joinPoint){
        joinPoint.setUserAttribute("startTime_" + joinPoint.getMethod().getName(),System.currentTimeMillis());
        //这个方法中的逻辑，是由我们自己写的
        log.info("Invoker Before Method!!!" +
                "\nTargetObject:" +  joinPoint.getThis() +
                "\nArgs:" + Arrays.toString(joinPoint.arguments()));
    }

    //在调用一个方法之后，执行after方法
    public void after(WJoinPiont joinPoint){
        log.info("Invoker After Method!!!" +
                "\nTargetObject:" +  joinPoint.getThis() +
                "\nArgs:" + Arrays.toString(joinPoint.arguments()));
        long startTime = (Long) joinPoint.getUserAttribute("startTime_" + joinPoint.getMethod().getName());
        long endTime = System.currentTimeMillis();
        System.out.println("use time :" + (endTime - startTime));
    }

    public void afterThrowing(WJoinPiont joinPoint, Throwable ex){
        log.info("出现异常" +
                "\nTargetObject:" +  joinPoint.getThis() +
                "\nArgs:" + Arrays.toString(joinPoint.arguments()) +
                "\nThrows:" + ex.getMessage());
    }

    public void afterReturn(){

    }


}
