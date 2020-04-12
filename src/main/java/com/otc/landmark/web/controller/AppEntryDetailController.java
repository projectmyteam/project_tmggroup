package com.otc.landmark.web.controller;

import com.otc.landmark.web.constant.UrlConst;
import com.otc.landmark.web.dto.CommentDto;
import com.otc.landmark.web.dto.EntryDto;
import com.otc.landmark.web.repository.CategoryDao;
import com.otc.landmark.web.repository.impl.CategoryDaoImpl;
import com.otc.landmark.web.service.CommentService;
import com.otc.landmark.web.service.EntryService;
import com.otc.landmark.web.service.impl.CommentServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
			Integer countComment = commentService.countComment(entryId);
			Integer limit = 3;
			List<CommentDto> commentDtos = commentService.comments(entryId, limit);
			mav.addObject("countComment", countComment);
			mav.addObject("comments", commentDtos);
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

	@RequestMapping(value = "/comment/add", method = RequestMethod.POST)
	public ResponseEntity<CommentDto> commentAdd(@RequestBody CommentDto commentDto) {
		commentService.addComment(commentDto);
		if(commentDto.getResult().equals("true")) {
			return new ResponseEntity<CommentDto>(commentDto, HttpStatus.OK);
		}
		return new ResponseEntity<CommentDto>(commentDto, HttpStatus.OK);
	}

	@RequestMapping(value = "/comment/more", method = RequestMethod.GET)
	public ResponseEntity<List<CommentDto>> commentMore(@RequestParam(value = "first")Integer first,
														@RequestParam(value = "max", defaultValue = "3") Integer max,
														@RequestParam(value = "entryId")Long entryId) {
		List<CommentDto> commentDtos = commentService.getCommentLimit(entryId, first, max);
		return new ResponseEntity<List<CommentDto>>(commentDtos, HttpStatus.OK);
	}

	@Autowired
	private CommentServiceImpl commentService;

}
