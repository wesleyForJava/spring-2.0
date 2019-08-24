package com.wesley.prac.spring.formework.aop;

import com.wesley.prac.spring.formework.aop.invercation.WReflectiveMethodInvocation;
import com.wesley.prac.spring.formework.aop.support.WAdvisorSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class WJDKDynamicAopProxy implements WAopProxy,InvocationHandler {


    private WAdvisorSupport advisored;

    public  WJDKDynamicAopProxy(WAdvisorSupport advisored){
        this.advisored=advisored;
    }

    @Override
    public Object getProxy() {
        return getProxy(this.advisored.getTargetClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        Proxy.newProxyInstance(classLoader,this.advisored.getTargetClass().getInterfaces(),this);
        return null;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<Object> interceptorsAndDynamicInterceptionAdvice = this.advisored.getInterceptorsAndDynamicInterceptionAdvice(method, this.advisored.getTargetClass());
        WReflectiveMethodInvocation methodInvocation=
                new WReflectiveMethodInvocation(proxy,null,
                        method,args,this.advisored.getTargetClass(),
                        interceptorsAndDynamicInterceptionAdvice);

        return methodInvocation.proceed();
    }
}
