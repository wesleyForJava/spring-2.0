package com.wesley.prac.spring.formework.aop.aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class WAbstractAspectAdvice implements WAdvice{
    private Method aspectMethod;

    private Object target;

    public WAbstractAspectAdvice(Method aspectMethod,Object target) {
        this.aspectMethod=aspectMethod;
        this.target=target;
    }


    public  Object invokeAdviceMethod(WJoinPiont joinPiont,Object returnValue,Throwable tx) throws InvocationTargetException, IllegalAccessException {
        Class<?>[] parameterTypes = this.aspectMethod.getParameterTypes();
        if (parameterTypes==null || parameterTypes.length==0) {
               return aspectMethod.invoke(target, null);
        }else {
            Object[] args = new Object[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                if (parameterTypes[i].equals(WJoinPiont.class)) {
                    args[i]=joinPiont;
                }else if (parameterTypes[i].equals(Throwable.class)){
                    args[i]=tx;
                }else if(parameterTypes[i].equals(Object.class)){
                    args[i]=returnValue;
                }
            }
              return   this.aspectMethod.invoke(target,args);
        }
    }


}
