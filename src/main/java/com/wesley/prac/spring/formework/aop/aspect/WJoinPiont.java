package com.wesley.prac.spring.formework.aop.aspect;

import java.lang.reflect.Method;

public interface WJoinPiont {

     Method getMethod();

     Object getThis();

     Object [] arguments();

     void setUserAttribute(String key,Object value);


     Object getUserAttribute(String key);




}
