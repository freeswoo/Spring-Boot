package com.biz.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	// @ResponseBody => 리턴되는 값을 바로 웹으로 전송
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String home() {
		return "home";
	}
}
