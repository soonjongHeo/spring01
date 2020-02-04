package com.example.spring01.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter { 
	
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override 
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception { 
		logger.info("====================================== START ======================================"); 
		logger.info(" Request URI \t: " + request.getRequestURI()); 
		
		//세션 객체 생성
		HttpSession session=request.getSession();
		//세션이 없으면(로그인되지 않은 상태)
		if(session.getAttribute("userid") == null) {
			//login 페이지로 이동
			response.sendRedirect(request.getContextPath()
					+"/member/login.do?message=nologin");
			return false; //메인 액션으로 가지 않음
		}else {
			return true; //메인 액션으로 이동
		}
	} 
	
	@Override public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception { 
		logger.info("====================================== END ======================================\n");
		super.postHandle(request, response, handler, modelAndView);
	} 
	
}

