package com.otc.landmark.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/doc")
public class AppDocumentController {

	@RequestMapping
	public ModelAndView showLogin(HttpServletRequest req) throws Exception {
		return new ModelAndView("otc.document.view");
	}
}
