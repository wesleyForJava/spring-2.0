package com.wesley.prac.spring.formework.aop.support;

import com.wesley.prac.spring.formework.aop.aspect.AfterReturingAdviceInteceptor;
import com.wesley.prac.spring.formework.aop.aspect.AfterThowingAdviceInteceptor;
import com.wesley.prac.spring.formework.aop.aspect.WMethodBeforeAdviceInteceptor;
import com.wesley.prac.spring.formework.aop.config.WAopConfig;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WAdvisorSupport {

    private Class<?> targetClass;

    private Object target;

    private WAopConfig config;

    private Map<Method,List<Object>> methodCache;

    private Pattern pointCutClassPattern;

    public WAdvisorSupport(WAopConfig config) {
           this.config=config;

    }

    public Class<?> getTargetClass(){
        return this.targetClass;
    }

    public Object getTarget(){
        return this.target;
    }
    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method, Class<?> targetClass) throws Exception{
        List<Object> cached = this.methodCache.get(method);
        if (cached==null) {
          Method  m= targetClass.getMethod(method.getName(), method.getParameterTypes());
//            cached = methodCache.get(m);
            this.methodCache.put(m,cached);
        }
        return cached;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
        parse();
    }

    private void parse() {

        String pointCut = config.getPointCut()
                .replaceAll("\\.","\\\\.")
                .replaceAll("\\\\.\\*",".*")
                .replaceAll("\\(","\\\\(")
                .replaceAll("\\)","\\\\)");
//        pointCut=public .* com.wesley.prac.spring.demo.service..*Service..*(.*)
        String pointCutForClassRegex = pointCut.substring(0, pointCut.lastIndexOf("\\(") - 4);
        pointCutClassPattern= Pattern.compile("class"
                +pointCutForClassRegex.substring(
                        pointCutForClassRegex.lastIndexOf(" ")+1));

        Pattern compile = Pattern.compile(pointCut);
        Map<String, Method> aspectMethods=null;
        Class<?> aspectClass=null;
        methodCache=new HashMap<Method, List<Object>>();
        try {
            aspectClass = Class.forName(config.getAspectClass());
             aspectMethods = new HashMap<String, Method>();

            for (Method method : aspectClass.getMethods()) {
               aspectMethods.put(method.getName(),method);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //把每个方法都包装成MethodInteceptor
        for (Method method : this.getTargetClass().getMethods()) {

            String methodString = method.toString();
            if (methodString.contains("throws")) {
                methodString = methodString.substring(0, methodString.indexOf("throws")).trim();
            }
            Matcher matcher = compile.matcher(methodString);

            if (matcher.matches()) {
                LinkedList<Object> advices = new LinkedList<Object>();
                //before
                if (!(config.getAspectBefore().equals("")||config.getAspectBefore()==null)) {
                    //创建一个advice对象
                    try {
                        advices.add(new WMethodBeforeAdviceInteceptor(aspectMethods.get(config.getAspectBefore()),aspectClass.newInstance()));
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                //after
                if (!(config.getAspectAfter()==null || config.getAspectAfter().equals(""))) {
                    try {
                        advices.add(new AfterReturingAdviceInteceptor(aspectMethods.get(config.getAspectAfter()),aspectClass.newInstance()));
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                }
                //throw
                if (!(config.getAspectAfterThrow()==null || config.getAspectAfterThrow().equals(""))) {
                    try {
                        AfterThowingAdviceInteceptor thowingAdvice = new AfterThowingAdviceInteceptor(aspectMethods.get(config.getAspectAfterThrow()), aspectClass.newInstance());
                        thowingAdvice.setThrowName(config.getAspectAfterThrowingName());
                        advices.add(thowingAdvice);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                methodCache.put(method,advices);
            }
        }
    }
    public void setTarget(Object target) {
        this.target = target;
    }

    public boolean pointCutMatch() {
        return this.pointCutClassPattern.matcher(this.getTargetClass().toString()).matches();
    }





}
