package com.otc.landmark.web.service;

import javax.servlet.http.HttpServletRequest;

import com.otc.landmark.web.domain.User;
import com.otc.landmark.web.dto.UserDto;

public interface UserService {
	
	public void saveUser(HttpServletRequest req, UserDto userDto) throws Exception;
	public UserDto updateUser(HttpServletRequest req, UserDto userDto, User user) throws Exception;
	public void updateUserPass(HttpServletRequest req, UserDto userDto, User user) throws Exception;
	public UserDto findByUsername(String userName);
}
