package com.otc.landmark.web.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.otc.landmark.web.constant.UrlConst;
import com.otc.landmark.web.domain.Category;
import com.otc.landmark.web.repository.CategoryDao;

@Controller
@RequestMapping(value = UrlConst.SERVICE)
public class AppServiceController {
	
	@Autowired
	CategoryDao categoryDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView service(@RequestParam(value = "categoryId") Long categoryId) throws Exception {
		//Handle redirect
		ModelAndView mav = new ModelAndView("otc.web.service.view");
		Category masterCategory = categoryDao.findById(categoryId);
		List<Category> services = categoryDao.findSubCategory(categoryId);
		
		mav.addObject("masterCategory", masterCategory);
		mav.addObject("services", services);
		
		return mav;
	}
}
