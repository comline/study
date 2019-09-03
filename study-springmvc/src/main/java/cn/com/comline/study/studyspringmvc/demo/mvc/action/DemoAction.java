package cn.com.comline.study.studyspringmvc.demo.mvc.action;

import cn.com.comline.study.studyspringmvc.demo.service.IDemoService;
import cn.com.comline.study.studyspringmvc.springmvc.annotation.CAutowired;
import cn.com.comline.study.studyspringmvc.springmvc.annotation.CController;
import cn.com.comline.study.studyspringmvc.springmvc.annotation.CRequestMapping;
import cn.com.comline.study.studyspringmvc.springmvc.annotation.CRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CController
@CRequestMapping("/demo")
public class DemoAction {

  	@CAutowired
	private IDemoService demoService;

	@CRequestMapping("/query.*")
	public void query(HttpServletRequest req, HttpServletResponse resp,
					  @CRequestParam("name") String name){
		String result = demoService.get(name);
//		String result = "My name is " + name;
		try {
			resp.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@CRequestMapping("/add")
	public void add(HttpServletRequest req, HttpServletResponse resp,
					@CRequestParam("a") Integer a, @CRequestParam("b") Integer b){
		try {
			resp.getWriter().write(a + "+" + b + "=" + (a + b));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@CRequestMapping("/remove")
	public void remove(HttpServletRequest req,HttpServletResponse resp,
					   @CRequestParam("id") Integer id){
	}

}
