package com.wesley.prac.spring.formework.aop.aspect;

import com.wesley.prac.spring.formework.aop.invercation.WMethodInteceptor;
import com.wesley.prac.spring.formework.aop.invercation.WReflectiveMethodInvocation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AfterAroundAdviceInteceptor extends WAbstractAspectAdvice implements WMethodInteceptor {

    private WJoinPiont joinPiont;

    public AfterAroundAdviceInteceptor(Method aspectMethod, Object target) {
        super(aspectMethod, target);
    }

    @Override
    public Object invoke(WReflectiveMethodInvocation methodInvocation) throws Throwable {
        System.out.println("环绕之前");
        Object retVal = methodInvocation.proceed();
        methodInvocation.getMethod().invoke(methodInvocation.getMethod(),methodInvocation.getThis());
        System.out.println("环绕之后");
        return null;
    }



}
