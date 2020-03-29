package com.otc.landmark.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@RequestMapping
	public ModelAndView showLogin(HttpServletRequest req) throws Exception {
		return new ModelAndView("otc.login.view");
	}
	
}
