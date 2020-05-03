package com.otc.landmark.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.otc.landmark.web.Utils.Utility;
import com.otc.landmark.web.domain.User;
import com.otc.landmark.web.dto.UserDto;
import com.otc.landmark.web.repository.UserDao;
import com.otc.landmark.web.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserDao userDao;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest req) throws Exception {
//		ModelAndView mav = new ModelAndView("otc.register.view");
		ModelAndView mav = new ModelAndView("otc.registers.view");
		mav.addObject("newUserDto", new UserDto());
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView handleRegister(@ModelAttribute(value = "newUserDto") UserDto newUserDto, RedirectAttributes redirectAttributes, HttpServletRequest req) throws Exception {
		ModelAndView mavRe =  new ModelAndView("otc.registers.view");
		ModelAndView mavLi = new ModelAndView("otc.register.success.view");
		//check null value
		if(newUserDto.getUserName() != null && newUserDto.getFullName() != null && newUserDto.getEmail() != null
				&& newUserDto.getTelephone() != null && newUserDto.getPassword() != null && newUserDto.getConfirmPassword() != null) {
			//check 2 password
			if(newUserDto.getPassword().compareTo(newUserDto.getConfirmPassword()) == 0) {
				//check user exist
				User user = userDao.findByUserName(newUserDto.getUserName());
				if(user == null) {
					//check email valid or not
					if(Utility.isValidEmail(newUserDto.getEmail())) {
						//check email or sdt
						if(!userDao.checkExistEmailOrPhone(newUserDto.getEmail(), newUserDto.getTelephone(), (long) -1)) {
							userService.saveUser(req, newUserDto);
							mavLi.addObject("error", "Đăng ký tài khoản thành công!");
							return mavLi;
						}else {
							mavRe.addObject("error", "Email/Số điện thoại đã tồn tại!!!");
						}
					}else {
						mavRe.addObject("error", "Địa chỉ email không đúng!!!");
					}
				}else {
					mavRe.addObject("error", "Tên đăng nhập đã tồn tại!!!");
				}
			}else {
				mavRe.addObject("error", "Xác nhận mật khẩu không trùng!!!");
			}
		}else {
			mavRe.addObject("error", "Vui lòng nhập đầy đủ thông tin!!!");
		}
		return mavRe;
	}
}
