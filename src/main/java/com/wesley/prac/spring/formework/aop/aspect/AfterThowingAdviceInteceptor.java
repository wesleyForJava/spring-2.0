package com.wesley.prac.spring.formework.aop.aspect;

import com.wesley.prac.spring.formework.aop.invercation.WMethodInteceptor;
import com.wesley.prac.spring.formework.aop.invercation.WReflectiveMethodInvocation;

import java.lang.reflect.Method;

public class AfterThowingAdviceInteceptor extends WAbstractAspectAdvice implements WMethodInteceptor {


    private String throwName;

    public AfterThowingAdviceInteceptor(Method aspectMethod, Object target) {
        super(aspectMethod, target);
    }

    @Override
    public Object invoke(WReflectiveMethodInvocation methodInvocation) throws Throwable {
            try {
                return methodInvocation.proceed();
            }catch (Throwable e){
              super.invokeAdviceMethod(methodInvocation,null,e.getCause());
              throw  e;
            }
    }
    public  void  setThrowName(String throwName){
     this.throwName=throwName;
    }

}
