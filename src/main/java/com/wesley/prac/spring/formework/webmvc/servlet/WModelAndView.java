package com.wesley.prac.spring.formework.webmvc.servlet;

import java.util.Map;


public class WModelAndView {

    private String viewName;
    private Map<String,?> model;

    public WModelAndView(String viewName) { this.viewName = viewName; }

    public WModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public String getViewName() {
        return viewName;
    }

//    public void setViewName(String viewName) {
//        this.viewName = viewName;
//    }

    public Map<String, ?> getModel() {
        return model;
    }

//    public void setModel(Map<String, ?> model) {
//        this.model = model;
//    }
}
