package com.wesley.prac.spring.demo.action;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wesley.prac.spring.formework.annotation.WController;
import com.wesley.prac.spring.formework.annotation.WRequestMapping;
import com.wesley.prac.spring.formework.annotation.WRequestParam;
import com.wesley.prac.spring.formework.webmvc.servlet.WModelAndView;
import com.wesley.prac.spring.demo.service.IModifyService;
import com.wesley.prac.spring.demo.service.IQueryService;
import com.wesley.prac.spring.formework.annotation.WAutowired;



@WController
@WRequestMapping("/web")
public class MyAction {

	@WAutowired
	IQueryService queryService;
	@WAutowired
	IModifyService modifyService;

	@WRequestMapping("/query.json")
	public WModelAndView query(HttpServletRequest request, HttpServletResponse response,
							   @WRequestParam("name") String name){
		String result = queryService.query(name);
		return out(response,result);
	}
	
	@WRequestMapping("/add*.json")
	public WModelAndView add(HttpServletRequest request,HttpServletResponse response,
			   @WRequestParam("name") String name,@WRequestParam("addr") String addr){
		String result = null;
		try {
			result = modifyService.add(name,addr);
			return out(response,result);
		} catch (Exception e) {
//			e.printStackTrace();
			Map<String,Object> model = new HashMap<String,Object>();
			model.put("detail",e.getMessage());
//			System.out.println(Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]",""));
			model.put("stackTrace", Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]",""));
			return new WModelAndView("500",model);
		}

	}
	
	@WRequestMapping("/remove.json")
	public WModelAndView remove(HttpServletRequest request,HttpServletResponse response,
		   @WRequestParam("id") Integer id){
		String result = modifyService.remove(id);
		return out(response,result);
	}
	
	@WRequestMapping("/edit.json")
	public WModelAndView edit(HttpServletRequest request,HttpServletResponse response,
			@WRequestParam("id") Integer id,
			@WRequestParam("name") String name){
		String result = modifyService.edit(id,name);
		return out(response,result);
	}
	
	
	
	private WModelAndView out(HttpServletResponse resp,String str){
		try {
			resp.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
