package com.otc.landmark.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.otc.landmark.web.constant.UrlConst;

@Controller
@RequestMapping(value = UrlConst.ENTRY_LIST)
public class AppEntryListController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView service() {
		ModelAndView mav = new ModelAndView("otc.web.entry.list.view");
		return mav;
	}
}
