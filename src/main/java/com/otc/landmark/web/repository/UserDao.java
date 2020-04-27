package com.otc.landmark.web.repository;

import com.otc.landmark.web.domain.User;

public interface UserDao {

	public User findByUserName(String userName);
	public boolean checkExistEmailOrPhone(String email, String telephone, Long userId);
	User findById(Long userId);
	public void save(User user);
}
