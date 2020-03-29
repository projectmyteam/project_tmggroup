package com.otc.landmark.web.security;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

public class CustomCookieClearingLogoutHandler implements LogoutHandler{
	
	private final List<String> cookiesToClear;
	
	public CustomCookieClearingLogoutHandler(List<String> cookiesToClear) {
		this.cookiesToClear = cookiesToClear;
	}

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		for(String cookieName : cookiesToClear) {
			Cookie cookie = new Cookie(cookieName, null);
			String cookiePath = request.getContextPath();
			cookie.setPath(cookiePath);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}	
	}

}
