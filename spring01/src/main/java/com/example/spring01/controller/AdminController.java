package com.example.spring01.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring01.model.dto.MemberDTO;
import com.example.spring01.service.AdminService;

@Controller
public class AdminController {

	private static final Logger logger = 
			LoggerFactory.getLogger(AdminController.class);
	
	@Inject
	AdminService adminService;
	
	@RequestMapping("admin/list.do")
	public String list(Model model) {
		logger.info("관리자 목록");
		List<MemberDTO> list = adminService.memberList();
		model.addAttribute("list", list);
		return "admin/list";
	}
	
	@RequestMapping("admin/write.do")
	public String write() {
		logger.info("관리자 등록페이지");
		return "admin/write";
	}
	
	@RequestMapping("admin/insert.do")
	public String insert(@ModelAttribute MemberDTO dto) {
		logger.info("관리자 등록");
		adminService.insertMember(dto);
		return "redirect:/admin/list.do";
	}
	
	@RequestMapping("admin/view.do")
	public String view(@RequestParam String userid, Model model) {
		logger.info("관리자 상세");
		MemberDTO viewMember = adminService.viewMember(userid);
		model.addAttribute("viewMember", viewMember);
		return "admin/view";
	}
	
	@RequestMapping("admin/update.do")
	public String update(@ModelAttribute MemberDTO dto, Model model) {
		logger.info("관리자 수정");
		boolean result = adminService.checkPw(dto.getUserid(), dto.getPasswd());
		if(result) {
			adminService.updateMember(dto);
			return "redirect:/admin/list.do";
		}else {
			MemberDTO viewMember = adminService.viewMember(dto.getUserid());
			dto.setJoinDate(viewMember.getJoinDate());
			model.addAttribute("viewMember", dto);
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			return "admin/view";
		} 
	}
	
	@RequestMapping("admin/delete.do")
	public String delete(@RequestParam String userid, @RequestParam String passwd,
			Model model) {
		logger.info("관리자 삭제");
		boolean result = adminService.checkPw(userid, passwd);
		if(result) {
			adminService.deleteMember(userid);
			return "redirect:/admin/list.do";
		}else {
			model.addAttribute("viewMember", adminService.viewMember(userid));
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			return "admin/view";
		} 
	}
	
	@RequestMapping("admin/login.do")
	public String login(Model model) {
		logger.info("관리자로그인 페이지");
		return "admin/login";
	}
	
	@RequestMapping("admin/loginCheck.do")
	public ModelAndView loginCheck(MemberDTO dto, HttpSession session, ModelAndView mav) {
		logger.info("관리자로그인check");
		String name = adminService.loginCheck(dto);
		if(name != null) {
			session.setAttribute("adminUserid", dto.getUserid());
			session.setAttribute("adminName", name);
			mav.setViewName("admin/admin");
			mav.addObject("message", "success");
		}else {
			System.out.println("message: error");
			mav.setViewName("admin/login");
			mav.addObject("message", "error");
			
		}
		return mav;
	}
	
	@RequestMapping("admin/logout.do")
	public String logout(HttpSession session) {
		logger.info("관리자로그아웃");
		session.invalidate();
		return "redirect:/admin/login.do";
	}
}
