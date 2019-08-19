package com.wesley;

import com.wesley.prac.spring.demo.action.MyAction;
import com.wesley.prac.spring.formework.context.WApplicationContext;

/**
 * Created by Tom on 2019/4/13.
 */
public class Test {

    public static void main(String[] args) {

        WApplicationContext context = new WApplicationContext("classpath:application.properties");
        try {
            Object object = context.getBean(MyAction.class);
            System.out.println(object);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
