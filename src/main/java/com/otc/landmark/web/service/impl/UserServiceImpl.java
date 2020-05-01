package com.otc.landmark.web.service.impl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otc.landmark.web.Utils.DateUtil;
import com.otc.landmark.web.Utils.UtilsUploadFile;
import com.otc.landmark.web.constant.CommonConst;
import com.otc.landmark.web.domain.Role;
import com.otc.landmark.web.domain.User;
import com.otc.landmark.web.dto.UserDto;
import com.otc.landmark.web.repository.RoleDao;
import com.otc.landmark.web.repository.UserDao;
import com.otc.landmark.web.service.UserService;

@Service
@Transactional(rollbackFor = Exception.class)
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
			user.setAvatar("/static/images/default-image.jpg");
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

	@Override
	public UserDto findByUsername(String userName) {
		User user = userDao.findByUserName(userName);
		if(user != null) {
			UserDto userDto = new UserDto();
			userDto.setUserId(user.getUserId());
			userDto.setUserName(user.getUserName());
			userDto.setFullName(user.getFullName());
			userDto.setEmail(user.getEmail());
			userDto.setTelephone(user.getTelephone());
			userDto.setAddress(user.getAddress());
			userDto.setAvatarPath(user.getAvatar());
			return userDto;
		}
		return null;
	}

	@Override
	public UserDto updateUser(HttpServletRequest req, UserDto userDto, User user) throws Exception {
		try {
			user.setUserName(userDto.getUserName());
			user.setFullName(userDto.getFullName());
//			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//			String hashedPassword = passwordEncoder.encode(userDto.getPassword());
//			user.setPassword(hashedPassword);
			user.setEmail(userDto.getEmail());
			user.setTelephone(userDto.getTelephone());
			user.setAddress(userDto.getAddress());
			if(userDto.getAvatar() != null) {
				//upload avatar
				String pathFile = UtilsUploadFile.uploadFile(req, userDto.getAvatar(),
						CommonConst.UPLOAD_USER_FILE);
				user.setAvatar(pathFile);
				userDto.setAvatarPath(pathFile);
			}
			user.setUpdatedBy(1L);
			user.setUpdatedDate(DateUtil.getSystemDateTime());
			
			userDao.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("System Error. Please contact admin for further assistant");
		}
		return userDto;
	}

	@Override
	public void updateUserPass(HttpServletRequest req, UserDto userDto, User user) throws Exception {
		try {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(userDto.getConfirmPassword());
			user.setPassword(hashedPassword);
			
			user.setUpdatedBy(1L);
			user.setUpdatedDate(DateUtil.getSystemDateTime());
			
			userDao.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("System Error. Please contact admin for further assistant");
		}
	}
}
