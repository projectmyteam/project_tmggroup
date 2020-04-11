package com.otc.landmark.web.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.otc.landmark.web.constant.CommonConst;
import com.otc.landmark.web.constant.UrlConst;
import com.otc.landmark.web.domain.Category;
import com.otc.landmark.web.domain.Entry;
import com.otc.landmark.web.dto.CategoryDto;
import com.otc.landmark.web.dto.EntryDto;
import com.otc.landmark.web.dto.PageWrapperDto;
import com.otc.landmark.web.repository.CategoryDao;
import com.otc.landmark.web.repository.EntryDao;
import com.otc.landmark.web.service.EntryService;

@Controller
@RequestMapping(value = UrlConst.ENTRY_DETAIL)
public class AppEntryDetailController {
	
	private static final Log logger = LogFactory.getLog(AppEntryDetailController.class);
	
	@Autowired
	EntryService entryService;
	
	@Autowired
	CategoryDao categoryDao;
	
	@RequestMapping(value = "/{entryId}",method = RequestMethod.GET)
	public ModelAndView entryDetailGet(@PathVariable(value = "entryId") Long entryId) {
		ModelAndView mav = new ModelAndView("otc.web.entry.detail.view");
		//Handle redirect
		try {
			EntryDto entryDto = entryService.getById(entryId);
			mav.addObject("entry", entryDto);
			
			List<EntryDto> relativeEntries = entryService.getRelativeEntries(entryId, entryDto.getCategoryDto().getCategoryId());
			mav.addObject("newestEntries", relativeEntries);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return mav;
	}
	
	@RequestMapping(value = UrlConst.CONTACT ,method = RequestMethod.GET)
	public ModelAndView getContact(@RequestParam(value = "categoryId") Long categoryId) {
		ModelAndView mav = new ModelAndView("otc.web.entry.detail.view");
		//Handle redirect
		try {
			List<EntryDto> entryDtos = entryService.getEntryBySubCategoryId(categoryId);
			if(entryDtos.isEmpty()) {
				mav.addObject("entry", null);
			}else {
				mav.addObject("entry", entryDtos.get(0));
			}
						
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return mav;
	}

}
