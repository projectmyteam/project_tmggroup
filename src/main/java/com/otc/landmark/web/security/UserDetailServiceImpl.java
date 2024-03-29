package com.otc.landmark.web.security;

import com.otc.landmark.web.domain.Role;
import com.otc.landmark.web.domain.User;
import com.otc.landmark.web.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException("Không tìm thấy tài khoản");
		}
		
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		if(user.getRoles() != null && !user.getRoles().isEmpty()) {
			for(Role role : user.getRoles()) {
				authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
			}
		}
		
		CustomUserDetail userDetail = new CustomUserDetail(user.getUserName(), user.getPassword(), authorities);
		userDetail.setFullName(user.getFullName());
		userDetail.setAddress(user.getAddress());
		userDetail.setEmail(user.getEmail());
		userDetail.setTelephone(user.getTelephone());
		userDetail.setAvatar(user.getAvatar());

		return userDetail;
	}

	public void updateAuthenticationByUsername(String username) throws UsernameNotFoundException {
		CustomUserDetail userUpdate = (CustomUserDetail) loadUserByUsername(username);
    	Authentication authentication = new UsernamePasswordAuthenticationToken(userUpdate, userUpdate.getPassword(), userUpdate.getAuthorities());
    	SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
