package com.otc.landmark.admin.controller;

import com.otc.landmark.web.Utils.Utils;
import com.otc.landmark.web.constant.Message;
import com.otc.landmark.web.constant.MessageList;
import com.otc.landmark.web.constant.UrlConst;
import com.otc.landmark.web.domain.Category;
import com.otc.landmark.web.domain.Entry;
import com.otc.landmark.web.dto.EntryDto;
import com.otc.landmark.web.dto.OptgroupDto;
import com.otc.landmark.web.dto.OptionDto;
import com.otc.landmark.web.dto.Select2Dto;
import com.otc.landmark.web.exception.ConstraintException;
import com.otc.landmark.web.repository.CategoryDao;
import com.otc.landmark.web.repository.EntryDao;
import com.otc.landmark.web.service.EntryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(UrlConst.ADMIN + UrlConst.BLOG)
public class BlogPostingController {
	
	private static final Log logger = LogFactory.getLog(BlogPostingController.class);

	@Autowired
	private EntryDao entryDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private EntryService entryService;

	@RequestMapping(value = UrlConst.LIST, method = RequestMethod.GET)
	public ModelAndView list(Model model)  {
		ModelAndView mav = new ModelAndView("otc.admin.blog.list.view");
		//If redirect
		Map<String, Object> md = model.asMap();
		MessageList messageList = (MessageList) md.get("messageList");
		mav.addObject("messageList", messageList);
				
		List<Entry> entryList =  entryDao.findAll();
		mav.addObject("entryList", entryList);
		return mav;
	}
	
	@RequestMapping(value = UrlConst.ADD, method = RequestMethod.GET)
	public ModelAndView createAddBlogForm()  {
		ModelAndView mav = new ModelAndView("otc.admin.blog.add.view");
		EntryDto newEntryDto = new EntryDto();
		mav.addObject("newEntryDto", newEntryDto);
		return mav;
	}

	@RequestMapping(value = UrlConst.ADD ,method = RequestMethod.POST)
	public ModelAndView submitAddBlogForm (@ModelAttribute(value = "newEntryDto") EntryDto newEntryDto, RedirectAttributes redirectAttributes, HttpServletRequest req) {
		
		ModelAndView mav = new ModelAndView("otc.admin.blog.add.view");
		MessageList messageList = new MessageList(Message.SUCCESS);
		
		try {
			entryService.saveEntry(req, newEntryDto);
		} catch (Exception e) {
			logger.error("Lưu bài viết thất bại");
			messageList.setStatus(Message.ERROR);
			messageList.add(e.getMessage());
			mav.addObject("messageList", messageList);
			return mav;
		}
			
		messageList.add("Thêm bài viết thành công");	
		redirectAttributes.addAttribute("id", newEntryDto.getId());
		redirectAttributes.addFlashAttribute("messageList", messageList);
		
		String viewName = UrlConst.REDIRECT.concat(UrlConst.ADMIN).concat(UrlConst.BLOG).concat(UrlConst.LIST);
		mav.setViewName(viewName);
		return mav;
	}
	
	@RequestMapping(value = UrlConst.EDIT, method = RequestMethod.GET)
	public ModelAndView createEditBlogForm(@RequestParam(value = "id") Long entryId, HttpServletRequest req, Model model)  {
		ModelAndView mav = new ModelAndView("otc.admin.blog.edit.view");
		
		//If redirect
		Map<String, Object> md = model.asMap();
		MessageList messageList = (MessageList) md.get("messageList");
		mav.addObject("messageList", messageList);
		
		EntryDto editEntryDto = new EntryDto();
		List<Category> lev1Categories = new ArrayList<Category>();
		List<Category> subCategories = new ArrayList<Category>();
		try {
			editEntryDto = entryService.getById(entryId);
			buildCategoryDropDown(lev1Categories, subCategories, editEntryDto.getCategoryId());
		}catch(Exception e) {
			mav = new ModelAndView("otc.admin.error.view");
			return mav;
		}
		
		mav.addObject("editEntryDto", editEntryDto);		
		mav.addObject("lev1Categories", lev1Categories);	
		mav.addObject("subCategories", subCategories);
		
		return mav;
	}
	
	@RequestMapping(value = UrlConst.EDIT, method = RequestMethod.POST)
	public ModelAndView submitEditBlogForm(@ModelAttribute(value = "editEntryDto") EntryDto editEntryDto, HttpServletRequest req)  {
		ModelAndView mav = new ModelAndView("otc.admin.blog.edit.view");
		MessageList messageList = new MessageList(Message.SUCCESS);
		
		List<Category> lev1Categories = new ArrayList<Category>();
		List<Category> subCategories = new ArrayList<Category>();
		try {
			entryService.updateEntry(req, editEntryDto);
			buildCategoryDropDown(lev1Categories, subCategories, editEntryDto.getCategoryId());
		} catch (Exception e) {
			logger.error("Cập nhật bài viết thất bại");
			messageList.setStatus(Message.ERROR);
			messageList.add(e.getMessage());
			mav.addObject("messageList", messageList);
			return mav;
		}
		
		messageList.add("Cập nhật bài viết thành công");
		mav.addObject("messageList", messageList);	
		mav.addObject("editEntryDto", editEntryDto);		
		mav.addObject("lev1Categories", lev1Categories);	
		mav.addObject("subCategories", subCategories);
		return mav;
	}

	@RequestMapping(value = UrlConst.VIEW, method = RequestMethod.GET)
	public ModelAndView viewBlog(@RequestParam(value = "id") Long entryId)  {
		ModelAndView mav = new ModelAndView("otc.admin.blog.view.view");
		
		Entry viewEntry = null;
		List<Category> lev1Categories = new ArrayList<Category>();
		List<Category> subCategories = new ArrayList<Category>();
		
		try {
			viewEntry = entryDao.findById(entryId);
			buildCategoryDropDown(lev1Categories, subCategories, viewEntry.getCategoryId());
		}catch(Exception e) {
			mav = new ModelAndView("otc.admin.error.view");
			return mav;
		}
		
		mav.addObject("viewEntry", viewEntry);
		mav.addObject("lev1Categories", lev1Categories);
		mav.addObject("subCategories", subCategories);
		return mav;
	}
	
	@RequestMapping(value = UrlConst.DELETE, method = RequestMethod.POST)
	public ModelAndView deleteBlog(@RequestParam(value = "id") Long id, @RequestParam(value = "forceDel", required = false) boolean forceDel, 
			RedirectAttributes redirectAttributes, HttpServletResponse response)  {
		ModelAndView mav = new ModelAndView(UrlConst.REDIRECT + UrlConst.ADMIN + UrlConst.BLOG + UrlConst.LIST);
		MessageList messageList = new MessageList(Message.SUCCESS);
		try {
			entryService.deleteEntry(id, forceDel);
		}catch (ConstraintException e) {	
			try {
				response.setStatus(400);
				response.getWriter().write(e.getMessage());
				throw new RuntimeException();
			} catch (IOException e1) {
				logger.error(e);
			}
		}catch (Exception e) {
			mav = new ModelAndView("otc.admin.error.view");
			return mav;
		}
		messageList.add("Xóa bài viết thành công");
		redirectAttributes.addFlashAttribute("messageList", messageList);
		return mav;
	}
	
	@RequestMapping(value = UrlConst.AJAX + "/getSubCategory", method = RequestMethod.GET)
	@ResponseBody
	public Object getSubCategoryByAjax(@RequestParam(value = "categoryId", required = true) Long categoryId)  {
		List<Select2Dto> select2Dtos = new ArrayList<Select2Dto>();
		try {
			List<Category> subCategories = categoryDao.findSubCategory(categoryId);
			for(Category category : subCategories) {
				List<Category> childCategories = categoryDao.findSubCategory(category.getCategoryId());
				if(childCategories.size() > 0) {
					OptgroupDto optgroupDto = new OptgroupDto();
					optgroupDto.setText(Utils.upperCaseFirstChar(category.getCategoryName()));
					List<OptionDto> children = new ArrayList<OptionDto>();
					for(Category chilCategory : childCategories) {
						OptionDto optionDto = new OptionDto();
						optionDto.setId(String.valueOf(chilCategory.getCategoryId()));
						optionDto.setText(Utils.upperCaseFirstChar(chilCategory.getCategoryName()));
						children.add(optionDto);
					}
					optgroupDto.setChildren(children);
					select2Dtos.add(optgroupDto);
				}else {
					OptionDto optionDto = new OptionDto();
					optionDto.setId(String.valueOf(category.getCategoryId()));
					optionDto.setText(Utils.upperCaseFirstChar(category.getCategoryName()));
					select2Dtos.add(optionDto);
				}
			}
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return select2Dtos;
	}
	
	@RequestMapping(value = UrlConst.AJAX + "/getCategory", method = RequestMethod.GET)
	@ResponseBody
	public Object getCategoryByAjax()  {
		List<Select2Dto> select2Dtos = new ArrayList<Select2Dto>();
		try {
			List<Category> lev1Categories = categoryDao.findLevel1Category();
			for(Category category : lev1Categories) {
				OptionDto optionDto = new OptionDto();
				optionDto.setId(String.valueOf(category.getCategoryId()));
				optionDto.setText(Utils.upperCaseFirstChar(category.getCategoryName()));
				select2Dtos.add(optionDto);
			}
		}catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return select2Dtos;
	}
	
	@RequestMapping(value = UrlConst.AJAX + "/getEntry", method = RequestMethod.GET)
	@ResponseBody
	public Object getEntryByAjax(@RequestParam(value = "subCategoryId", required = true) Long subCategoryId)  {
		List<Select2Dto> select2Dtos = new ArrayList<Select2Dto>();
		try {
			List<Entry> entries = entryDao.findEntryBySubCateId(subCategoryId);
			if(entries != null && !entries.isEmpty()) {
				for(Entry entry : entries) {
					OptionDto optionDto = new OptionDto();
					optionDto.setId(String.valueOf(entry.getId()));
					optionDto.setText(entry.getSubject());
					select2Dtos.add(optionDto);
				}
			}
		} catch (Exception e) {
			logger.error("System Error");
		}
		
		return select2Dtos;
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
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView test() throws Exception  {
		ModelAndView mav = new ModelAndView("otc.admin.blog.test.view");
		entryDao.findEntryAndNewsById(1L);
//		EntryDto entryDto = entryService.getById(20L);
//		mav.addObject("entryDto", entryDto);
		return mav;
	}
	
	public void buildCategoryDropDown(List<Category> lev1Categories, List<Category> subCategories, Long categoryId) throws Exception {
		lev1Categories.addAll(categoryDao.findLevel1Category());
		subCategories.addAll(findSubCategoryMenu(categoryDao.findSubCategory(categoryId)));
	}
	
}
