package com.wesley.prac.spring.formework.aop;

import com.wesley.prac.spring.formework.aop.support.WAdvisorSupport;

public class WCglibAopProxy implements WAopProxy {
    public WCglibAopProxy(WAdvisorSupport config) {

    }

    @Override
    public Object getProxy() {
        return null;
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return null;
    }
}
