package com.otc.landmark.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.otc.landmark.web.Utils.Utility;
import com.otc.landmark.web.Utils.Utils;
import com.otc.landmark.web.Utils.UtilsUploadFile;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.otc.landmark.web.constant.UrlConst;
import com.otc.landmark.web.domain.Category;
import com.otc.landmark.web.domain.Entry;
import com.otc.landmark.web.domain.News;
import com.otc.landmark.web.dto.EntryDto;
import com.otc.landmark.web.repository.CategoryDao;
import com.otc.landmark.web.repository.EntryDao;
import com.otc.landmark.web.repository.NewsDao;
import com.otc.landmark.web.service.EntryService;

import javassist.NotFoundException;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(UrlConst.ROOT)
public class AppHomeController {
	private static final Log logger = LogFactory.getLog(AppHomeController.class);
	private static final String TGM_GROUP_CATEGORY_ID = "1";
	private static final String CHUNG_TOI_CO_CATEGORY_ID = "2";
	private static final String DOI_NGU_CATEGORY_ID = "7";

	@Autowired
	CategoryDao categoryDao;

	@Autowired
	EntryDao entryDao;
	
	@Autowired
	NewsDao newsDao;

	@Autowired
	EntryService entryService;

	//Set default page for project
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest req) throws Exception {
		ModelAndView mav = new ModelAndView("otc.web.homepage.view");
		
		//Show newest entry of ChungToiCo
		/*List<Category> ctcSubCategories = categoryDao.findSubCategory(Long.valueOf(CHUNG_TOI_CO_CATEGORY_ID));
		List<Entry> ctcNewestEntries = new ArrayList<Entry>();
		if(ctcSubCategories != null && !ctcSubCategories.isEmpty()) {
			for(Category  ctcSubCategory : ctcSubCategories) {
				if(ctcSubCategory.getCategoryCode().equals("chung-khoan")) {
					Entry entry = findNewestEntryOfMiddleCategory(ctcSubCategory.getCategoryId());
					if(entry != null) {
						entry.setSubCategory(ctcSubCategory);
						ctcNewestEntries.add(entry);
					}
					continue;
				}
				Entry entry = entryDao.findNewestEntry(ctcSubCategory.getCategoryId());
				if(entry != null) {
					entry.setSubCategory(ctcSubCategory);
					ctcNewestEntries.add(entry);
				}
			}
		}
		mav.addObject("ctcNewestEntries", ctcNewestEntries);*/
		
		//Show newest entry of BanDangTim
		/*List<Entry> bdtEntries = entryDao.findEntryByParentId(3L);		
		if(bdtEntries != null) {
			Map<Long, Entry> entryMap = new HashMap<>();
			for (Entry entry: bdtEntries) {
				Long key = entry.getSubCategoryId();
				if (entryMap.get(key)!=null){
					Entry valueInMap = entryMap.get(key);
					if (valueInMap.getCreatedDate().compareTo(entry.getCreatedDate()) < 0){
						entryMap.put(key, entry);
					}
				} else if (entryMap.get(key)==null){
					entryMap.put(key, entry);
				}
			}
			List<Entry> bdtNewestEntries = new ArrayList<>();
			for (Map.Entry<Long, Entry> entry: entryMap.entrySet()){
				bdtNewestEntries.add(entry.getValue());
			}
			mav.addObject("bdtNewestEntries", bdtNewestEntries);
		}	*/
		
		//Show newest entry of UuDaiThanhVien
		/*List<Entry> udtvEntries = entryDao.findEntryByParentId(4L);
		if(udtvEntries != null) {
			Map<Long, Entry> entryMap = new HashMap<Long, Entry>();
			for(Entry item : udtvEntries) {
				Long subCategoryId = item.getSubCategoryId();
				if(!entryMap.containsKey(subCategoryId)) {
					entryMap.put(item.getSubCategoryId(), item);
				}else {
					if(entryMap.get(subCategoryId).getCreatedDate().compareTo(item.getCreatedDate()) < 0) {
						entryMap.put(subCategoryId, item);
					}
				}
			}
			
			List<Entry> udtvNewestEntries = new ArrayList<Entry>();
			for(Map.Entry<Long, Entry> entry : entryMap.entrySet()) {
				udtvNewestEntries.add(entry.getValue());
			}
			
			mav.addObject("udtvNewestEntries", udtvNewestEntries);
		}*/
	
		
		//Show doingu
		/*Entry dnNewestEntry = entryDao.findNewestEntry(Long.valueOf(DOI_NGU_CATEGORY_ID));
		if(dnNewestEntry != null) {
			mav.addObject("dnNewestEntry", dnNewestEntry);
		}			
		
		//show banner
		List<News> banners = newsDao.findAll();
		if(banners != null && !banners.isEmpty()) {
			mav.addObject("banners", banners);
		}*/
		return mav;
	}

	@RequestMapping(value = UrlConst.ABOUT, method = RequestMethod.GET)
	public ModelAndView about()  {
		ModelAndView mav = new ModelAndView("otc.web.aboutpage.view");
		return mav;
	}

	@RequestMapping(value = UrlConst.MEMBER, method = RequestMethod.GET)
	public ModelAndView member()  {
		ModelAndView mav = new ModelAndView("otc.web.memberpage.view");
		return mav;
	}
	
	private Entry findNewestEntryOfMiddleCategory(Long middleCategoryId) throws Exception {
		List<Category> subCategories = categoryDao.findSubCategory(middleCategoryId);
		Long[] subCategoryIds = new Long[subCategories.size()];
		for(int i = 0; i < subCategories.size(); i++) {
			subCategoryIds[i] = subCategories.get(i).getCategoryId();
		}
		return entryDao.findNewestEntryByCategoryList(subCategoryIds);
	}
	
	@RequestMapping(value = UrlConst.DETERMINE_URL + "/{categoryId}", method = RequestMethod.GET)
	public ModelAndView determineUrl(@PathVariable(value = "categoryId", required = true) Long categoryId, RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView();
		String viewName = "";
		try {
			List<Category> childCategory = categoryDao.findSubCategory(categoryId);
			if(childCategory != null && !childCategory.isEmpty()) {
				viewName = UrlConst.REDIRECT.concat(UrlConst.SERVICE);
				mav.setViewName(viewName);
			}else {
				viewName = UrlConst.REDIRECT.concat(UrlConst.ENTRY_LIST);
				mav.setViewName(viewName);
			}
		} catch (Exception e) {
			logger.error(e);
			mav.setViewName("otc.web.homepage.view");
			return mav;
		}
		
		//Pass request param (as String)
		redirectAttributes.addAttribute("categoryId", categoryId);
		return mav;
	}
	
	@RequestMapping(value = UrlConst.AJAX + UrlConst.MENU, method = RequestMethod.GET)
	@ResponseBody
	public Object getMenuObject() {
		List<Category> allCategories = categoryDao.findAll();
		List<Category> masterCategories = new ArrayList<Category>();		
		if(allCategories != null && !allCategories.isEmpty()) {
			for(Category category : allCategories) {
				if(category.getParentCategoryId() == null) {
					Category masterCategory = category;
					masterCategory.setChildsCategory(getChildCategory(masterCategory.getCategoryId(), allCategories));
					masterCategories.add(category);
				}
			}
		}
		
		return masterCategories;
	}

	private List<Category> getChildCategory(Long parentId, List<Category> allCategories) {
		List<Category> childCategories = new ArrayList<Category>();
		for(Category category : allCategories) {
			if(category.getParentCategoryId() == parentId) {
				category.setChildsCategory(getChildCategory(category.getCategoryId(), allCategories));
				childCategories.add(category);			
			}
		}
		
		return childCategories;
	}

}
