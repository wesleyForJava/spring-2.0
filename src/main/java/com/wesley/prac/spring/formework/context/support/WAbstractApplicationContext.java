package com.wesley.prac.spring.formework.context.support;

/**
 * IOC容器实现的顶层设计
 */
public abstract class WAbstractApplicationContext {
    //受保护，只提供给子类重写
    public void refresh() throws Exception {}
}
