package com.otc.landmark.web.controller;

import com.otc.landmark.web.Utils.Utils;
import com.otc.landmark.web.constant.CommonConst;
import com.otc.landmark.web.constant.UrlConst;
import com.otc.landmark.web.domain.Category;
import com.otc.landmark.web.dto.EntryDto;
import com.otc.landmark.web.dto.PageWrapperDto;
import com.otc.landmark.web.repository.CategoryDao;
import com.otc.landmark.web.repository.EntryDao;
import com.otc.landmark.web.service.EntryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppDetailController {
	
	private static final Log logger = LogFactory.getLog(AppDetailController.class);

	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private EntryDao entryDao;
	@Autowired
	private EntryService entryService;

	@RequestMapping(value = UrlConst.ALLDETAIL + "/{categoryId}", method = RequestMethod.GET)
	public ModelAndView allDetail(@PathVariable(value = "categoryId", required = false) Long categoryId) throws Exception {
		ModelAndView mav = new ModelAndView("otc.web.alldetailpage.view");
		
		Category category = categoryDao.findById(categoryId);
		category = Utils.upperCaseFirstCharsetCate(category);
		mav.addObject("category", category);

		List<Category> subCategoryList = categoryDao.findSubCategory(categoryId);
		// get subCategory if category have childs
		if (subCategoryList.size() > 0) {
			Utils.upperCaseFirstCharsetListCate(subCategoryList);
			mav.addObject("subCategoryList", subCategoryList);
			mav.addObject("catehavechild", "true");
		}
		// get entry if category have not childs
		else {
			EntryDto entryDto = new EntryDto();
			entryDto.setSubCategoryId(categoryId);
			PageWrapperDto<EntryDto> pageWrapperDto = null;
			try {
				pageWrapperDto = entryService.search(CommonConst.DEFAULT_PAGE, CommonConst.DEFAULT_PAGESIZE, entryDto);
			} catch (Exception e) {
				logger.error(e);
				pageWrapperDto = new PageWrapperDto<EntryDto>();
				mav.addObject("pageWrapperDto", pageWrapperDto);
				mav.addObject("catehavechild", "false");
				return mav;
			}
			mav.addObject("pageWrapperDto", pageWrapperDto);
			if(pageWrapperDto == null) {
                mav.addObject("catehavechild", "false");
            }
		}
		return mav;
	}
	
	@RequestMapping(value = UrlConst.ALLDETAIL + "/{categoryId}", method = RequestMethod.POST)
	public ModelAndView ajaxPostallDetail(@PathVariable(value = "categoryId") Long categoryId,
			@RequestParam(value = "page") int page, @RequestParam(value = "pageSize") int pageSize) {
		ModelAndView mav = new ModelAndView("otc.web.alldetailpage.view");
	
		PageWrapperDto<EntryDto> pageWrapperDto = null;
		try {
			Category category = categoryDao.findById(categoryId);
			category = Utils.upperCaseFirstCharsetCate(category);
			mav.addObject("category", category);

			EntryDto entryDto = new EntryDto();
			entryDto.setSubCategoryId(categoryId);
			pageWrapperDto = entryService.search(page, pageSize, entryDto);
		} catch (Exception e) {
			logger.error(e);
			pageWrapperDto = new PageWrapperDto<EntryDto>();
			mav.addObject("pageWrapperDto", pageWrapperDto);
			mav.addObject("catehavechild", "false");
			return mav;
		}

		mav.addObject("pageWrapperDto", pageWrapperDto);
		mav.addObject("catehavechild", "false");
		
		return mav;
	}

//    @RequestMapping(value = UrlConst.DETAIL+"/{identry}/{idsubcate}", method = RequestMethod.GET)
//    public ModelAndView detail(@PathVariable(value = "identry", required = false) Long identry
//            , @PathVariable(value = "idsubcate", required = false) Long idsubcate) throws Exception {
//        ModelAndView view = new ModelAndView("otc.web.detailpage.view");
//        //get entry by id entry
//        Entry entry = entryDao.findById(identry);
//        view.addObject("entry", entry);
//        //get name category by subcategoryid
//        Category category = categoryDao.findById(entry.getCategory().getCategoryId());
//        Utils.upperCaseFirstCharsetCate(category);
//        view.addObject("category", category);
//        //get entry relationship
//        List<Entry> entryList = entryDao.findEntryLimit(idsubcate, identry);
//        List<Entry> entryListcustomDate = new ArrayList<>();
//        for (Entry entrylimit : entryList) {
//            String pattern = "MM-dd-yyyy";
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//            String date = simpleDateFormat.format(entry.getCreatedDate());
////            entrylimit.setDateFormatString(date);
//            entryListcustomDate.add(entrylimit);
//        }
//		view.addObject("entrylist", entryListcustomDate);
//
//        double entryLimit = entry.getBody().length() / 1.4;
//        double entryOpacity = entry.getBody().length() / 2;
//        view.addObject("entryLimit", entryLimit);
//        view.addObject("entryOpacity", entryOpacity);
//
//        return view;
//    }

}
