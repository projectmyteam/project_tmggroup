package com.otc.landmark.web.repository;

import com.otc.landmark.web.domain.User;

public interface UserDao {

	public User findByUserName(String userName);
	
}
