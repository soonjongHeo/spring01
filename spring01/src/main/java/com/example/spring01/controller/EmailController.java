package com.example.spring01.controller;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring01.common.common.CommandMap;
import com.example.spring01.model.dto.EmailDTO;
import com.example.spring01.service.EmailService;

@Controller
@RequestMapping("email/*")  
public class EmailController {
	@Inject
	EmailService emailService;
	
	@RequestMapping("write.do")
	public String write() {
		return "/email/write";
	}
	
	@RequestMapping("send.do")
	public String send(CommandMap commandMap, HttpServletRequest request, Model model) {
		try {
			emailService.sendMail(commandMap.getMap(), request);
			model.addAttribute("message","이메일이 발송되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message","이메일 발송 실패...");
		}
		return "/email/write";
	}
}