package com.otc.landmark.admin.controller;

import com.otc.landmark.web.constant.UrlConst;
import com.otc.landmark.web.repository.CategoryDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(UrlConst.ADMIN)
public class AdminHomeController {
	private static final Log logger = LogFactory.getLog(AdminHomeController.class);
	
	@Autowired
	CategoryDao categoryDao;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView defaultUrl()  {
		ModelAndView mav = new ModelAndView();
		String viewName = UrlConst.REDIRECT.concat(UrlConst.ADMIN).concat(UrlConst.INFO);
		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value = UrlConst.INFO, method = RequestMethod.GET)
	public ModelAndView info()  {
		ModelAndView mav = new ModelAndView("otc.admin.home.view");
		return mav;
	}

}
