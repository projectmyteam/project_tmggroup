package com.otc.landmark.admin.controller;

import com.otc.landmark.web.Utils.Utils;
import com.otc.landmark.web.constant.Message;
import com.otc.landmark.web.constant.MessageList;
import com.otc.landmark.web.constant.UrlConst;
import com.otc.landmark.web.domain.Category;
import com.otc.landmark.web.domain.Entry;
import com.otc.landmark.web.domain.News;
import com.otc.landmark.web.dto.NewsDto;
import com.otc.landmark.web.repository.CategoryDao;
import com.otc.landmark.web.repository.EntryDao;
import com.otc.landmark.web.repository.NewsDao;
import com.otc.landmark.web.service.NewsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(UrlConst.ADMIN + UrlConst.NEWS)
public class NewsPostingController {
	
	private static final Log logger = LogFactory.getLog(NewsPostingController.class);
	
	@Autowired
    NewsDao newsDao;
	
	@Autowired
    private CategoryDao categoryDao;
	
	@Autowired
    EntryDao entryDao;
	
	@Autowired
    NewsService newsService;
	
	@RequestMapping(value = UrlConst.LIST, method = RequestMethod.GET)
	public ModelAndView list(Model model) {
		ModelAndView mav = new ModelAndView("otc.admin.news.list.view");
		//If redirect
		Map<String, Object> md = model.asMap();
		MessageList messageList = (MessageList) md.get("messageList");
		mav.addObject("messageList", messageList);
		
		List<News> newsList = newsDao.findAll();
		mav.addObject("newsList", newsList);
		return mav;
	}
	
	@RequestMapping(value = UrlConst.ADD, method = RequestMethod.GET)
	public ModelAndView createAddNewsForm() {
		ModelAndView mav = new ModelAndView("otc.admin.news.add.view");
		NewsDto newsDto = new NewsDto();
		mav.addObject("newsDto", newsDto);
		return mav;
	}
	
	@RequestMapping(value = UrlConst.ADD, method = RequestMethod.POST)
	public ModelAndView submitAddNewsForm(HttpServletRequest req, @ModelAttribute(value = "newsDto") NewsDto newsDto, RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView("otc.admin.news.add.view");
		MessageList messageList = new MessageList(Message.SUCCESS);
		
		try {
			newsService.saveNews(req, newsDto);
		}catch (Exception e) {
			logger.error("Lưu bản tin thất bại");
			messageList.setStatus(Message.ERROR);
			messageList.add(e.getMessage());
			mav.addObject("messageList", messageList);
			return mav;
		}
	
		messageList.add("Thêm bản tin thành công");
		redirectAttributes.addFlashAttribute("messageList", messageList);
		String viewName = UrlConst.REDIRECT.concat(UrlConst.ADMIN).concat(UrlConst.NEWS).concat(UrlConst.LIST);
		mav.setViewName(viewName);
		return mav;
	}
	
	@RequestMapping(value = UrlConst.EDIT, method = RequestMethod.GET)
	public ModelAndView createEditNewsForm(@RequestParam(value = "id") Long newsId) {
		ModelAndView mav = new ModelAndView("otc.admin.news.edit.view");
		NewsDto editNewsDto = new NewsDto();
		List<Category> lev1Categories = new ArrayList<Category>();
		List<Category> subCategories = new ArrayList<Category>();
		List<Entry> entries = new ArrayList<Entry>();
		try {
			editNewsDto = newsService.getById(newsId);
			buildCategoryDropDown(lev1Categories, subCategories, editNewsDto.getCategoryId());
			entries = entryDao.findEntryBySubCateId(editNewsDto.getSubCategoryId());
		} catch (Exception e) {
			mav = new ModelAndView("otc.admin.error.view");
			return mav;
		}
		mav.addObject("editNewsDto", editNewsDto);
		
		mav.addObject("lev1Categories", lev1Categories);
		mav.addObject("subCategories", subCategories);
		mav.addObject("entries", entries);
		return mav;
	}
	
	@RequestMapping(value = UrlConst.EDIT, method = RequestMethod.POST)
	public ModelAndView submitEditNewsForm(@ModelAttribute(value = "editNewsDto") NewsDto editNewsDto, HttpServletRequest req)  {
		ModelAndView mav = new ModelAndView("otc.admin.news.edit.view");
		MessageList messageList = new MessageList(Message.SUCCESS);
		List<Category> lev1Categories = new ArrayList<Category>();
		List<Category> subCategories = new ArrayList<Category>();
		List<Entry> entries = new ArrayList<Entry>();
		try {
			newsService.updateNews(req, editNewsDto);
			buildCategoryDropDown(lev1Categories, subCategories, editNewsDto.getCategoryId());
			entries = entryDao.findEntryBySubCateId(editNewsDto.getSubCategoryId());
		} catch (Exception e) {
			logger.error("Cập nhật bản tin thất bại");
			messageList.setStatus(Message.ERROR);
			messageList.add(e.getMessage());
			mav.addObject("messageList", messageList);
			return mav;
		}
		
		messageList.add("Cập nhật bản tin thành công");
		mav.addObject("messageList", messageList);
		mav.addObject("editNewsDto", editNewsDto);	
		mav.addObject("lev1Categories", lev1Categories);
		mav.addObject("subCategories", subCategories);
		mav.addObject("entries", entries);
		return mav;
	}
	
	@RequestMapping(value = UrlConst.VIEW, method = RequestMethod.GET)
	public ModelAndView viewNews(@RequestParam(value = "id") Long newId)  {
		ModelAndView mav = new ModelAndView("otc.admin.news.view.view");
		
		NewsDto viewNewsDto = new NewsDto();
		List<Category> lev1Categories = new ArrayList<Category>();
		List<Category> subCategories = new ArrayList<Category>();
		List<Entry> entries = new ArrayList<Entry>();
		
		try {
			viewNewsDto = newsService.getById(newId);
			buildCategoryDropDown(lev1Categories, subCategories, viewNewsDto.getCategoryId());
			entries = entryDao.findEntryBySubCateId(viewNewsDto.getSubCategoryId());
		}catch(Exception e) {
			mav = new ModelAndView("otc.admin.error.view");
			return mav;
		}
		
		mav.addObject("viewNewsDto", viewNewsDto);
		mav.addObject("lev1Categories", lev1Categories);
		mav.addObject("subCategories", subCategories);
		mav.addObject("entries", entries);
		return mav;
	}
	
	@RequestMapping(value = UrlConst.DELETE, method = RequestMethod.POST)
	public ModelAndView deleteNews(@RequestParam(value = "id") Long id, RedirectAttributes redirectAttributes)  {
		ModelAndView mav = new ModelAndView(UrlConst.REDIRECT + UrlConst.ADMIN + UrlConst.NEWS + UrlConst.LIST);
		MessageList messageList = new MessageList(Message.SUCCESS);
		try {
			newsService.deleteNews(id);
		} catch (Exception e) {
			mav = new ModelAndView("otc.admin.error.view");
			return mav;
		}
		messageList.add("Xóa bàn tin thành công");
		redirectAttributes.addFlashAttribute("messageList", messageList);
		return mav;
	}
	
	public List<Category> findSubCategoryMenu(List<Category> subCategories) throws Exception{
		List<Category> subCategoryMenu = new ArrayList<Category>();
		for(Category subCategory : subCategories) {
			subCategory.setCategoryName(Utils.upperCaseFirstChar(subCategory.getCategoryName()));
			List<Category> childCategories = categoryDao.findSubCategory(subCategory.getCategoryId());
			if(childCategories != null && childCategories.size() > 0) {
				subCategory.setChildsCategory(childCategories);
				subCategoryMenu.add(subCategory);
			}else {
				subCategoryMenu.add(subCategory);
			}
		}
		return subCategoryMenu;
	}
	
	public void buildCategoryDropDown(List<Category> lev1Categories, List<Category> subCategories, Long categoryId) throws Exception {
		lev1Categories.addAll(categoryDao.findLevel1Category());
		subCategories.addAll(findSubCategoryMenu(categoryDao.findSubCategory(categoryId)));
	}
}
