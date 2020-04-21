package com.otc.landmark.admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.otc.landmark.web.constant.Message;
import com.otc.landmark.web.constant.MessageList;
import com.otc.landmark.web.constant.UrlConst;
import com.otc.landmark.web.dto.DocumentDto;
import com.otc.landmark.web.exception.ConstraintException;
import com.otc.landmark.web.service.DocumentService;

@Controller
@RequestMapping(UrlConst.ADMIN + UrlConst.DOCS)
public class DocumentController {

	private static final Log logger = LogFactory.getLog(DocumentController.class);
	
	@Autowired
	DocumentService docService;
	
	@RequestMapping(value = UrlConst.LIST, method = RequestMethod.GET)
	public ModelAndView listDocument(Model model) throws Exception {
		ModelAndView mav = new ModelAndView("otc.admin.docs.list.view");
		//If redirect
		Map<String, Object> md = model.asMap();
		MessageList messageList = (MessageList) md.get("messageList");
		mav.addObject("messageList", messageList);
				
		List<DocumentDto> docDto =  docService.findAll(1);
		mav.addObject("docList", docDto);
		return mav;
	}
	
	@RequestMapping(value = UrlConst.ADD, method = RequestMethod.GET)
	public ModelAndView addDocumentForm()  {
		ModelAndView mav = new ModelAndView("otc.admin.docs.add.view");
		DocumentDto docsDto = new DocumentDto();
		mav.addObject("docsDto", docsDto);
		return mav;
	}
	
	@RequestMapping(value = UrlConst.ADD ,method = RequestMethod.POST)
	public ModelAndView submitAddDocumentForm (@Valid @ModelAttribute(value = "docsDto") DocumentDto docDto, BindingResult bindingResult, 
			RedirectAttributes redirectAttributes, HttpServletRequest req) {
		
		ModelAndView mav = new ModelAndView("otc.admin.docs.add.view");
		MessageList messageList = new MessageList(Message.SUCCESS);
		
		if(bindingResult.hasErrors()) {
			messageList.setStatus(Message.ERROR);
			messageList.add("Thông tin được nhập không hợp lệ");
			mav.addObject("messageList", messageList);
			return mav;
		}
				
		try {
			//code at here
			docService.saveDocument(req, docDto);
		} catch (Exception e) {
			logger.error("Lưu tài liệu thất bại");
			messageList.setStatus(Message.ERROR);
			messageList.add(e.getMessage());
			mav.addObject("messageList", messageList);
			return mav;
		}
			
		messageList.add("Thêm tài liệu thành công");	
		redirectAttributes.addFlashAttribute("messageList", messageList);
		
		String viewName = UrlConst.REDIRECT.concat(UrlConst.ADMIN).concat(UrlConst.DOCS).concat(UrlConst.LIST);
		mav.setViewName(viewName);
		return mav;
	}
	
	@RequestMapping(value = UrlConst.VIEW, method = RequestMethod.GET)
	public ModelAndView viewDocument(@RequestParam(value = "id") Long entryId)  {
		ModelAndView mav = new ModelAndView("otc.admin.docs.view.view");
		
		DocumentDto viewDocDto = null;		
		try {
			//0: img file, 1: fullLink file, 2: preview File
			viewDocDto = docService.findById(entryId, 1);
		}catch(Exception e) {
			mav = new ModelAndView("otc.admin.error.view");
			return mav;
		}
		
		mav.addObject("viewDocDto", viewDocDto);
		return mav;
	}
	
	@RequestMapping(value = UrlConst.EDIT, method = RequestMethod.GET)
	public ModelAndView editDocumentForm(@RequestParam(value = "id") Long docId, HttpServletRequest req, Model model)  {
		ModelAndView mav = new ModelAndView("otc.admin.docs.edit.view");
		
		//If redirect
		Map<String, Object> md = model.asMap();
		MessageList messageList = (MessageList) md.get("messageList");
		mav.addObject("messageList", messageList);
		
		DocumentDto editDocDto = new DocumentDto();
		try {
			editDocDto = docService.findById(docId, 1);
		}catch(Exception e) {
			mav = new ModelAndView("otc.admin.error.view");
			return mav;
		}
		
		mav.addObject("editDocDto", editDocDto);		
		
		return mav;
	}
	
	@RequestMapping(value = UrlConst.EDIT, method = RequestMethod.POST)
	public ModelAndView submitEditDocumentForm(@ModelAttribute(value = "editDocDto") DocumentDto editDocDto, HttpServletRequest req)  {
		ModelAndView mav = new ModelAndView("otc.admin.docs.edit.view");
		MessageList messageList = new MessageList(Message.SUCCESS);
		DocumentDto editDocDto1 = editDocDto;
		try {
			editDocDto1 = docService.updateDocument(req, editDocDto);
		} catch (Exception e) {
			logger.error("Cập nhật tài liệu thất bại");
			messageList.setStatus(Message.ERROR);
			messageList.add(e.getMessage());
			mav.addObject("messageList", messageList);
			return mav;
		}
		
		messageList.add("Cập nhật tài liệu thành công");
		mav.addObject("messageList", messageList);	
		mav.addObject("editDocDto", editDocDto1);	
		return mav;
	}
	
	@RequestMapping(value = UrlConst.DELETE, method = RequestMethod.POST)
	public ModelAndView deleteBlog(@RequestParam(value = "id") Long id, @RequestParam(value = "forceDel", required = false) boolean forceDel, 
			RedirectAttributes redirectAttributes, HttpServletRequest req, HttpServletResponse response)  {
		ModelAndView mav = new ModelAndView(UrlConst.REDIRECT + UrlConst.ADMIN + UrlConst.DOCS + UrlConst.LIST);
		MessageList messageList = new MessageList(Message.SUCCESS);
		try {
			//code at here
			docService.deleteDocument(req, id, forceDel);
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
		messageList.add("Xóa tài liệu thành công");
		redirectAttributes.addFlashAttribute("messageList", messageList);
		return mav;
	}
}
