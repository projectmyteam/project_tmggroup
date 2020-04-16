package com.otc.landmark.web.service;

import javax.servlet.http.HttpServletRequest;

import com.otc.landmark.web.dto.UserDto;

public interface UserService {
	
	public void saveUser(HttpServletRequest req, UserDto userDto) throws Exception;
}
