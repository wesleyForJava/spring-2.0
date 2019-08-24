package com.wesley.prac.spring.formework.aop.invercation;

import com.wesley.prac.spring.formework.aop.aspect.WJoinPiont;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WReflectiveMethodInvocation implements WJoinPiont {
    private Object proxy;
    private Method method;
    private Object target;
    private Object [] arguments;
    private List<Object> interceptorsAndDynamicMethodMatchers;
    private Class<?> targetClass;

    private Map<String, Object> userAttributes;

    //定义一个索引，从-1开始记录当前拦截器执行的位置
    private int currentInterceptorIndex=-1;

    public WReflectiveMethodInvocation(
            Object proxy, Object target, Method method, Object[] arguments,
            Class<?> targetClass, List<Object> interceptorsAndDynamicMethodMatchers) {

        this.proxy = proxy;
        this.target = target;
        this.targetClass = targetClass;
        this.method = method;
        this.arguments = arguments;
        this.interceptorsAndDynamicMethodMatchers = interceptorsAndDynamicMethodMatchers;
    }

    public Object proceed() throws Throwable{

        //如果interceptor执行完了则执行joinpoint
        if (this.currentInterceptorIndex==this.interceptorsAndDynamicMethodMatchers.size()-1) {
            return this.method.invoke(target, arguments);
        }

        Object interceptorOrInteceptorAdvice = this.interceptorsAndDynamicMethodMatchers.get(++this.currentInterceptorIndex);

        //如果要动态匹配JoinPoint
        if (interceptorOrInteceptorAdvice instanceof WMethodInteceptor) {
            WMethodInteceptor methodInteceptor= (WMethodInteceptor) interceptorOrInteceptorAdvice;
            return methodInteceptor.invoke(this);
        }else{
            return proceed();
        }
    }


    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public Object getThis() {
        return this.target;
    }

    @Override
    public Object[] arguments() {
        return this.arguments;
    }


    public void setUserAttribute(String key, Object value) {
        if (value != null) {
            if (this.userAttributes == null) {
                this.userAttributes = new HashMap<String,Object>();
            }
            this.userAttributes.put(key, value);
        }
        else {
            if (this.userAttributes != null) {
                this.userAttributes.remove(key);
            }
        }
    }


    public Object getUserAttribute(String key) {
        return (this.userAttributes != null ? this.userAttributes.get(key) : null);
    }
}
