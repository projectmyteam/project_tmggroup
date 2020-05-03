package com.otc.landmark.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.otc.landmark.admin.controller.DocumentController;
import com.otc.landmark.web.Utils.Utility;
import com.otc.landmark.web.constant.Message;
import com.otc.landmark.web.constant.MessageList;
import com.otc.landmark.web.constant.UrlConst;
import com.otc.landmark.web.dto.UserDto;
import com.otc.landmark.web.repository.UserDao;
import com.otc.landmark.web.security.UserDetailServiceImpl;
import com.otc.landmark.web.service.UserService;

@Controller
@RequestMapping(UrlConst.USER)
public class AppUserInfoController {

	private static final Log logger = LogFactory.getLog(DocumentController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserDetailServiceImpl userDetailService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showUserInfo(HttpServletRequest req) throws Exception {
		ModelAndView mav = new ModelAndView("otc.web.user.view");
		
		UserDto userDto = new UserDto();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	if(auth.getPrincipal() instanceof User) { 
    		User us=(User)(auth.getPrincipal());
    		userDto = userService.findByUsername(us.getUsername()); 
			//avatar default
    		if(userDto.getAvatarPath() == null) {
    			userDto.setAvatarPath("/static/images/default-image.jpg");
    		}
        	mav.addObject("userDto", userDto);
    	}else {
    		mav.setViewName("otc.web.homepage.view");
    	}
		return mav;
	}
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView changeUserInfo(@Valid @ModelAttribute(value = "userDto") UserDto userDto, HttpServletRequest req) throws Exception {
		ModelAndView mav = new ModelAndView("otc.web.user.view");
		MessageList messageList = new MessageList(Message.SUCCESS);
		
		UserDto userDto1 = new UserDto();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	if(auth.getPrincipal() instanceof User) {
    		User us=(User)(auth.getPrincipal());
    		if(userDto.getUserName() != null && userDto.getFullName() != null && userDto.getEmail() != null && userDto.getTelephone() != null) {
    			if(!userDto.getUserName().equals(us.getUsername())) {
        			com.otc.landmark.web.domain.User userChange = userDao.findByUserName(userDto.getUserName());
        			if(userChange != null) {
        				messageList.setStatus(Message.ERROR);
        				messageList.add("Tên đăng nhập đã tồn tại!!!");
        				mav.addObject("messageList", messageList);
        				return mav;
        			}
        		}
        		//check email valid or not
    			if(!Utility.isValidEmail(userDto.getEmail())) {
    				messageList.setStatus(Message.ERROR);
    				messageList.add("Địa chỉ email không đúng!!!");
    				mav.addObject("messageList", messageList);
    				return mav;
    			}else {
    				//check numberphone & email
    				com.otc.landmark.web.domain.User userdb = userDao.findByUserName(us.getUsername());
    				if(userdb.getEmail() !=null && userdb.getTelephone() != null &&
    						!userdb.getEmail().equals(userDto.getEmail()) || !userdb.getTelephone().equals(userDto.getTelephone())) {
    					if(userDao.checkExistEmailOrPhone(userDto.getEmail(), userDto.getTelephone(), userDto.getUserId())) {
    						messageList.setStatus(Message.ERROR);
    						messageList.add("Email/Số điện thoại đã tồn tại!!!");
    						mav.addObject("messageList", messageList);
    	    				return mav;
    					}
    				}
    				if(userdb != null) {
    		    		userDto1 = userService.updateUser(req, userDto, userdb); 
    					messageList.add("Cập nhập thông tin thành công");	
    			    	mav.addObject("messageList", messageList);
    			    	
    			    	userDetailService.updateAuthenticationByUsername(userDto.getUserName());
    	    		}
    			}
    		}else {
    			messageList.setStatus(Message.ERROR);
    			messageList.add("Vui lòng nhập đầy đủ thông tin!!!");
    			mav.addObject("messageList", messageList);
    		}
    	}else {
    		logger.error("Cập nhật tài liệu thất bại");
			messageList.setStatus(Message.ERROR);
			messageList.add("Lỗi chưa đăng nhập");
			mav.addObject("messageList", messageList);
    	}
    	mav.addObject("userDto", userDto1);
		return mav;
	}
}
