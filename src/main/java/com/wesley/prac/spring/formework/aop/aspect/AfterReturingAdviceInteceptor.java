package com.wesley.prac.spring.formework.aop.aspect;

import com.wesley.prac.spring.formework.aop.invercation.WMethodInteceptor;
import com.wesley.prac.spring.formework.aop.invercation.WReflectiveMethodInvocation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AfterReturingAdviceInteceptor extends WAbstractAspectAdvice implements WMethodInteceptor {

    private WJoinPiont joinPiont;

    public AfterReturingAdviceInteceptor(Method aspectMethod, Object target) {
        super(aspectMethod, target);
    }

    @Override
    public Object invoke(WReflectiveMethodInvocation methodInvocation) throws Throwable {
        this.joinPiont=methodInvocation;
        Object retVal = methodInvocation.proceed();
        this.afterReturn(retVal,methodInvocation.getMethod(),methodInvocation.arguments(),methodInvocation.getThis());
        return null;
    }

    public  void afterReturn(Object retVal, Method method, Object[] arguments, Object aThis){
        try {
            super.invokeAdviceMethod(joinPiont,retVal,null);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
