package com.wesley.prac.spring.formework.aop.aspect;

import com.wesley.prac.spring.formework.aop.invercation.WMethodInteceptor;
import com.wesley.prac.spring.formework.aop.invercation.WReflectiveMethodInvocation;

import java.lang.reflect.Method;

public class WMethodBeforeAdviceInteceptor extends WAbstractAspectAdvice implements WMethodInteceptor {

    private WJoinPiont joinPiont;


    public WMethodBeforeAdviceInteceptor(Method aspectMethod, Object target) {
        super(aspectMethod, target);
    }

   public void before(Method method,Object [] args,Object target)throws Throwable{
        //传送给织入的参数
//        method.invoke(target);
           super.invokeAdviceMethod(this.joinPiont,null,null);
    }

    @Override
    public Object invoke(WReflectiveMethodInvocation methodInvocation) throws Throwable {

        this.joinPiont=methodInvocation;
        //从被植入的代码中拿到 JoinPoint
        before(methodInvocation.getMethod(),methodInvocation.arguments(),methodInvocation.getThis());
        return methodInvocation.proceed();
    }
}
