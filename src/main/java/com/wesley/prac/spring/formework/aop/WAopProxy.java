package com.wesley.prac.spring.formework.aop;

public interface WAopProxy {

    Object getProxy();

    Object getProxy(ClassLoader classLoader);
}
