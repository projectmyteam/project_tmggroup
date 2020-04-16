package com.otc.landmark.web.service.impl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.otc.landmark.web.Utils.DateUtil;
import com.otc.landmark.web.domain.Role;
import com.otc.landmark.web.domain.User;
import com.otc.landmark.web.dto.UserDto;
import com.otc.landmark.web.repository.RoleDao;
import com.otc.landmark.web.repository.UserDao;
import com.otc.landmark.web.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Autowired
	RoleDao roleDao;
	
	@Override
	public void saveUser(HttpServletRequest req, UserDto userDto) throws Exception {
		try {
			User user = new User();
			user.setUserName(userDto.getUserName());
			user.setFullName(userDto.getFullName());
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(userDto.getPassword());
			user.setPassword(hashedPassword);
			user.setEmail(userDto.getEmail());
			user.setTelephone(userDto.getTelephone());
			user.setAddress(userDto.getAddress());
			//user.setEnabled("Y");
			//user.setAvatar();
			user.setCreatedBy(1L);
			user.setCreatedDate(DateUtil.getSystemDateTime());
			
			Role role = roleDao.findById((long)2);
			ArrayList<Role> roles = new ArrayList<Role>();
			roles.add(role);
			user.setRoles(roles);
			
			userDao.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("System Error. Please contact admin for further assistant");
		}
	}
}
