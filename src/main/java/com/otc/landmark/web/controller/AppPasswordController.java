package com.otc.landmark.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.otc.landmark.admin.controller.DocumentController;
import com.otc.landmark.web.constant.Message;
import com.otc.landmark.web.constant.MessageList;
import com.otc.landmark.web.dto.UserDto;
import com.otc.landmark.web.repository.UserDao;
import com.otc.landmark.web.service.UserService;

@Controller
@RequestMapping("/changePassword")
public class AppPasswordController {

	private static final Log logger = LogFactory.getLog(DocumentController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserDao userDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showChangePass(HttpServletRequest req) throws Exception {
		ModelAndView mav = new ModelAndView("otc.web.pass.view");
		
		UserDto userDto = new UserDto();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	if(auth.getPrincipal() instanceof User) { 
    		User us=(User)(auth.getPrincipal());
    		userDto = userService.findByUsername(us.getUsername()); 

    		mav.addObject("userDto", userDto);
    	}else {
    		mav.setViewName("otc.web.homepage.view");
    	}
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView changeUserInfo(@Valid @ModelAttribute(value = "userDto") UserDto userDto, HttpServletRequest req) throws Exception {
		ModelAndView mav = new ModelAndView("otc.web.pass.view");
		MessageList messageList = new MessageList(Message.SUCCESS);
		
		UserDto userDto1 = new UserDto();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	if(auth.getPrincipal() instanceof User) { 
    		if(!(userDto.getPassword().isEmpty() && userDto.getConfirmPassword().isEmpty() && userDto.getConfirmPassword1().isEmpty())) {
    			if(userDto.getConfirmPassword().equals(userDto.getConfirmPassword1())) {
        			User us=(User)(auth.getPrincipal());
            		com.otc.landmark.web.domain.User userdb = userDao.findByUserName(us.getUsername());
            		
            		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
            		if(encoder.matches(userDto.getPassword(), userdb.getPassword())){
            			userService.updateUserPass(req, userDto, userdb); 
            			messageList.add("Cập nhập mật khẩu thành công");	
            	    	mav.addObject("userDto", userDto1);
            		}else {
            			messageList.setStatus(Message.ERROR);
            			messageList.add("Mật khẩu cũ không chính xác");
            			mav.addObject("userDto", userDto);
            		}
        		}else {
        			messageList.setStatus(Message.ERROR);
        			messageList.add("Nhập lại mật khẩu mới không trùng");
        		}
    		}else {
    			messageList.setStatus(Message.ERROR);
    			messageList.add("Vui lòng nhập đầy đủ");
    		}
    	}else {
			messageList.setStatus(Message.ERROR);
			messageList.add("Lỗi chưa đăng nhập");
    	}
    	mav.addObject("messageList", messageList);
		return mav;
	}
}
