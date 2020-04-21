package com.otc.landmark.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.otc.landmark.web.dto.CommentDto;
import com.otc.landmark.web.dto.DocumentDto;
import com.otc.landmark.web.dto.EntryDto;
import com.otc.landmark.web.service.DocumentService;

@Controller
@RequestMapping("/doc")
public class AppDocumentController {

	private static final Log logger = LogFactory.getLog(AppEntryListController.class);

	@Autowired
	DocumentService docService;
	
	@RequestMapping
	public ModelAndView showDocument(HttpServletRequest req) throws Exception {
		ModelAndView mav = new ModelAndView("otc.web.document.view");
		//Handle redirect
		try {
			List<DocumentDto> docsList = docService.findAll(0); 			
			
			mav.addObject("docsList", docsList);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/{docId}",method = RequestMethod.GET)
	public ModelAndView docDetailGet(@PathVariable(value = "docId") Long docId) {
		ModelAndView mav = new ModelAndView("otc.web.document.detail.view");
		//Handle redirect
		try {
			DocumentDto docDto = docService.findById(docId, 3);
			mav.addObject("doc", docDto);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return mav;
	}
}
