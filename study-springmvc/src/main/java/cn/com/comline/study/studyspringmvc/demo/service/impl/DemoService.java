package cn.com.comline.study.studyspringmvc.demo.service.impl;

import cn.com.comline.study.studyspringmvc.demo.service.IDemoService;
import cn.com.comline.study.studyspringmvc.springmvc.annotation.CService;

/**
 * 核心业务逻辑
 */
@CService
public class DemoService implements IDemoService{

	public String get(String name) {
		return "My name is " + name;
	}

}
