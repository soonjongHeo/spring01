package com.example.spring01.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface EmailService {
	public void sendMail(Map<String, Object> map, HttpServletRequest request);
}
