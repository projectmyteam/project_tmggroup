package com.otc.landmark.web.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.otc.landmark.web.constant.CommonConst;
import com.otc.landmark.web.constant.UrlConst;
import com.otc.landmark.web.domain.Category;
import com.otc.landmark.web.domain.Entry;
import com.otc.landmark.web.dto.EntryDto;
import com.otc.landmark.web.dto.EntrySearchDto;
import com.otc.landmark.web.dto.PageWrapperDto;
import com.otc.landmark.web.repository.CategoryDao;
import com.otc.landmark.web.repository.EntryDao;
import com.otc.landmark.web.service.EntryService;

@Controller
@RequestMapping(value = UrlConst.ENTRY_LIST)
public class AppEntryListController {
	
	private static final Log logger = LogFactory.getLog(AppEntryListController.class);
	
	@Autowired
	EntryService entryService;
	
	@Autowired
	CategoryDao categoryDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView entryListGet(@RequestParam(value = "categoryId") Long categoryId) {
		ModelAndView mav = new ModelAndView("otc.web.entry.list.view");
		//Handle redirect
		try {
			Category category = categoryDao.findById(categoryId);
			mav.addObject("category", category);
			
			EntrySearchDto searchDto = new EntrySearchDto();
			searchDto.setSubCategoryId(categoryId);
			PageWrapperDto<EntryDto> pageWrapperDto = entryService.search(CommonConst.DEFAULT_PAGE, CommonConst.DEFAULT_PAGESIZE, searchDto);	
			mav.addObject("searchDto", searchDto);
			mav.addObject("pageWrapperDto", pageWrapperDto);
			
			List<EntryDto> newestEntries = entryService.getNewestEntries(categoryId);
			mav.addObject("newestEntries", newestEntries);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView entryListPost(@ModelAttribute(value = "searchDto") EntrySearchDto searchDto, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		ModelAndView mav = new ModelAndView("otc.web.entry.list.view");
		//Handle redirect
		try {
			page = (null == page) ? new Integer(1) : page;
			pageSize = (null == pageSize) ? new Integer(6) : pageSize;
			Category category = categoryDao.findById(searchDto.getSubCategoryId());
			PageWrapperDto<EntryDto> pageWrapperDto = entryService.search(page, pageSize, searchDto);
			
			mav.addObject("pageWrapperDto", pageWrapperDto);
			mav.addObject("category", category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	
}
