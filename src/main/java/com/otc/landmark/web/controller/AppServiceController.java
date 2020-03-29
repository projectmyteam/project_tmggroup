package com.otc.landmark.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.otc.landmark.web.constant.UrlConst;

@Controller
@RequestMapping(value = UrlConst.SERVICE)
public class AppServiceController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView service() {
		ModelAndView mav = new ModelAndView("otc.web.service.view");
		return mav;
	}
}
