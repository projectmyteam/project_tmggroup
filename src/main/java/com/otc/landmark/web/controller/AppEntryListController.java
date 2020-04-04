package com.otc.landmark.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.otc.landmark.web.constant.UrlConst;
import com.otc.landmark.web.domain.Category;
import com.otc.landmark.web.domain.Entry;
import com.otc.landmark.web.dto.EntryDto;
import com.otc.landmark.web.repository.CategoryDao;
import com.otc.landmark.web.repository.EntryDao;
import com.otc.landmark.web.service.EntryService;

@Controller
@RequestMapping(value = UrlConst.ENTRY_LIST)
public class AppEntryListController {
	
	@Autowired
	EntryService entryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView service(@RequestParam(value = "categoryId") Long categoryId) {
		ModelAndView mav = new ModelAndView("otc.web.entry.list.view");
		//Handle redirect
		try {
			List<EntryDto> entries = entryService.getEntryBySubCategoryId(categoryId);
			Category category = entries.get(0).getCategory();
			mav.addObject("entries", entries);
			mav.addObject("category", category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
}
