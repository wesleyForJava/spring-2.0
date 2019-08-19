package com.wesley.prac.spring.formework.beans.support;

import com.wesley.prac.spring.formework.beans.config.WBeanDefinition;
import com.wesley.prac.spring.formework.context.support.WAbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WDefaultListableBeanFactory extends WAbstractApplicationContext{

    //存储注册信息的BeanDefinition,伪IOC容器
    protected final Map<String, WBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, WBeanDefinition>();
}
