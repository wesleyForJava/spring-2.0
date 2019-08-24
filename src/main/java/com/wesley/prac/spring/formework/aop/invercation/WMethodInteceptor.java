package com.wesley.prac.spring.formework.aop.invercation;

public interface WMethodInteceptor {

    Object invoke(WReflectiveMethodInvocation methodInvocation) throws Throwable;
}
