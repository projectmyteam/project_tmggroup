package com.otc.landmark.admin.controller;

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

import com.otc.landmark.web.Utils.Utility;
import com.otc.landmark.web.constant.Message;
import com.otc.landmark.web.constant.MessageList;
import com.otc.landmark.web.constant.UrlConst;
import com.otc.landmark.web.dto.UserDto;
import com.otc.landmark.web.repository.CategoryDao;
import com.otc.landmark.web.repository.UserDao;
import com.otc.landmark.web.security.UserDetailServiceImpl;
import com.otc.landmark.web.service.UserService;

@Controller
@RequestMapping(UrlConst.ADMIN)
public class AdminHomeController {
	private static final Log logger = LogFactory.getLog(AdminHomeController.class);
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserDetailServiceImpl userDetailService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView defaultUrl()  {
		ModelAndView mav = new ModelAndView();
		String viewName = UrlConst.REDIRECT.concat(UrlConst.ADMIN).concat(UrlConst.INFO);
		mav.setViewName(viewName);
		return mav;
	}

	@RequestMapping(value = UrlConst.INFO, method = RequestMethod.GET)
	public ModelAndView info()  {
		ModelAndView mav = new ModelAndView("otc.admin.home.view");
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
	
	@RequestMapping(value = UrlConst.INFO, method = RequestMethod.POST)
	public ModelAndView changeInfoUserAdmin(@Valid @ModelAttribute(value = "userDto") UserDto userDto, HttpServletRequest req) throws Exception{
		ModelAndView mav = new ModelAndView("otc.admin.home.view");
		MessageList messageList = new MessageList(Message.SUCCESS);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	if(auth.getPrincipal() instanceof User) { 
    		User us=(User)(auth.getPrincipal());
    		UserDto userChange = userService.findByUsername(us.getUsername());
    		if(userDto.getPassword()!=null || userDto.getConfirmPassword()!=null) {
    			//change password
    			if(!(userDto.getPassword().isEmpty() && userDto.getConfirmPassword().isEmpty() && userDto.getConfirmPassword1().isEmpty())) {
        			if(userDto.getConfirmPassword().equals(userDto.getConfirmPassword1())) {
                		com.otc.landmark.web.domain.User userdb = userDao.findByUserName(us.getUsername());
                		
                		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
                		if(encoder.matches(userDto.getPassword(), userdb.getPassword())){
                			userService.updateUserPass(req, userDto, userdb); 
                			messageList.add("Cập nhập mật khẩu thành công");	
                	    	mav.addObject("userDto", userChange);
                		}else {
                			messageList.setStatus(Message.ERROR);
                			messageList.add("Mật khẩu cũ không chính xác");
                			mav.addObject("userDto", userChange);
                		}
            		}else {
            			messageList.setStatus(Message.ERROR);
            			messageList.add("Nhập lại mật khẩu mới không trùng");
            			mav.addObject("userDto", userChange);
            		}
        		}else {
        			messageList.setStatus(Message.ERROR);
        			messageList.add("Vui lòng nhập đầy đủ");
        			mav.addObject("userDto", userChange);
        		}
    		}else {
    			UserDto userDto1 = new UserDto();
    			//change user info
    			if(userDto.getUserName() != null && userDto.getFullName() != null && userDto.getEmail() != null && userDto.getTelephone() != null) {
        			if(!userDto.getUserName().equals(us.getUsername())) {
        				userChange = userService.findByUsername(userDto.getUserName());
            			if(userChange != null) {
            				messageList.setStatus(Message.ERROR);
            				messageList.add("Tên đăng nhập đã tồn tại!!!");
            				mav.addObject("messageList", messageList);
            				mav.addObject("userDto", userDto);
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
        			    	mav.addObject("userDto", userDto1);
        			    	
        			    	userDetailService.updateAuthenticationByUsername(userDto.getUserName());
        	    		}
        			}
        		}else {
        			messageList.setStatus(Message.ERROR);
        			messageList.add("Vui lòng nhập đầy đủ thông tin!!!");
        			mav.addObject("messageList", messageList);
        		}
    		}
    	}else {
    		mav.setViewName("otc.web.homepage.view");
    	}
		return mav;
	}	
}
