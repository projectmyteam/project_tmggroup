package com.otc.landmark.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.otc.landmark.web.repository.impl.CategoryDaoImpl;
import com.otc.landmark.web.service.impl.CategoryServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.otc.landmark.web.constant.UrlConst;
import com.otc.landmark.web.domain.Category;
import com.otc.landmark.web.domain.Entry;
import com.otc.landmark.web.dto.EntryDto;
import com.otc.landmark.web.dto.NewsDto;
import com.otc.landmark.web.repository.CategoryDao;
import com.otc.landmark.web.repository.EntryDao;
import com.otc.landmark.web.repository.NewsDao;
import com.otc.landmark.web.service.EntryService;

@Controller
@RequestMapping(UrlConst.ROOT)
public class AppHomeController {
	private static final Log logger = LogFactory.getLog(AppHomeController.class);
	private static final Long[] SERVICEBOX_IDS = new Long[] {13L, 14L, 15L, 16L, 17L, 18L , 23L};
	private static final Long CHUNG_KHOAN_CATEGORY_ID = 12L;
	private static final Long LIEN_HE_CATEGORY_ID = 6L;

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
		
		List<EntryDto> entryDtos = entryService.getAll();

		//Banner
		List<String> bannerImgs = new ArrayList<String>();
		List<NewsDto> banners = new ArrayList<NewsDto>();
		//Service box
		List<EntryDto> serviceBoxs = new ArrayList<EntryDto>();
		Map<Long, EntryDto> serviceBoxsMap = new HashMap<Long, EntryDto>();
		//Lastest news
		EntryDto[] newestEntry = new EntryDto[3];
		initNewestEntryDto(newestEntry);

		for(EntryDto entryDto : entryDtos) {
			if(entryDto.getNewsDto() != null) {
				bannerImgs.add(entryDto.getNewsDto().getAvatarPath());
				banners.add(entryDto.getNewsDto());
			}
			if(Arrays.asList(SERVICEBOX_IDS).contains(entryDto.getCategoryDto().getCategoryId())) {
				buildServiceBoxs(entryDto, serviceBoxsMap);
			}

			getNewestEntryDto(newestEntry, entryDto);
		}

		boolean firstTime = true;
		int fixedIndex = 0;
		for(Map.Entry<Long, EntryDto> entry : serviceBoxsMap.entrySet()) {
			Long parentCategoryId = entry.getValue().getCategoryDto().getParentCategoryId();
			if(parentCategoryId.equals(CHUNG_KHOAN_CATEGORY_ID)) {
				if(firstTime) {
					fixedIndex = serviceBoxs.size();
					serviceBoxs.add(entry.getValue());
					firstTime = false;
				}else {
					if(serviceBoxs.get(fixedIndex).getCreatedDate().compareTo(entry.getValue().getCreatedDate()) < 0) {
						serviceBoxs.remove(fixedIndex);
						serviceBoxs.add(fixedIndex, entry.getValue());
					}
				}
			}else {
				serviceBoxs.add(entry.getValue());
			}
		}


		mav.addObject("newestEntry", newestEntry);
		mav.addObject("serviceBoxs", serviceBoxs);
		mav.addObject("bannerImgs", bannerImgs);
		mav.addObject("banners", banners);

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


		return mav;
	}


	private void getNewestEntryDto(EntryDto[] newestEntry, EntryDto entryDto) {
		EntryDto first = newestEntry[0];
		EntryDto second = newestEntry[1];
		EntryDto third = newestEntry[2];
		if(entryDto.getCreatedDate().compareTo(first.getCreatedDate()) > 0) {
			newestEntry[0] = entryDto;
			newestEntry[1] = first;
			newestEntry[2] = second;
		}else if(entryDto.getCreatedDate().compareTo(second.getCreatedDate()) > 0) {
			newestEntry[1] = entryDto;
			newestEntry[2] = second;
		}else if(entryDto.getCreatedDate().compareTo(third.getCreatedDate()) > 0) {
			newestEntry[2] = entryDto;
		}
	}


	private void initNewestEntryDto(EntryDto[] newestEntryDto) {
		EntryDto minEntryDto = new EntryDto();
		minEntryDto.setCreatedDate(new Date(Long.MIN_VALUE));
		newestEntryDto[0] = minEntryDto;
		newestEntryDto[1] = minEntryDto;
		newestEntryDto[2] = minEntryDto;
	}


	private void buildServiceBoxs(EntryDto entryDto, Map<Long, EntryDto> serviceBoxsMap) {
		Long subcategoryId = entryDto.getCategoryDto().getCategoryId();
		if(!serviceBoxsMap.containsKey(subcategoryId)) {
			serviceBoxsMap.put(subcategoryId, entryDto);
		}else {
			if(serviceBoxsMap.get(subcategoryId).getCreatedDate().compareTo(entryDto.getCreatedDate()) < 0) {
				serviceBoxsMap.put(subcategoryId, entryDto);
			}
		}
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
			//handle special for Lien He page
			if(categoryId.equals(LIEN_HE_CATEGORY_ID)) {
				viewName = UrlConst.REDIRECT.concat(UrlConst.ENTRY_DETAIL).concat(UrlConst.CONTACT);
				mav.setViewName(viewName);
				//Pass request param (as String)
				redirectAttributes.addAttribute("categoryId", categoryId);
				return mav;
			}

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
