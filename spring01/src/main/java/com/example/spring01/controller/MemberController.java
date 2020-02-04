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
import com.example.spring01.service.MemberService;

@Controller
public class MemberController {

	private static final Logger logger = 
			LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService memberService;
	
	@RequestMapping("member/list.do")
	public String list(Model model) {
		logger.info("회원 목록");
		List<MemberDTO> list = memberService.memberList();
		model.addAttribute("list", list);
		return "member/list";
	}
	
	@RequestMapping("member/write.do")
	public String write() {
		logger.info("회원 등록페이지");
		return "member/write";
	}
	
	@RequestMapping("member/insert.do")
	public String insert(@ModelAttribute MemberDTO dto) {
		logger.info("회원 등록");
		memberService.insertMember(dto);
		return "redirect:/member/list.do";
	}
	
	@RequestMapping("member/view.do")
	public String view(@RequestParam String userid, Model model) {
		logger.info("회원 상세");
		MemberDTO viewMember = memberService.viewMember(userid);
		model.addAttribute("viewMember", viewMember);
		return "member/view";
	}
	
	@RequestMapping("member/update.do")
	public String update(@ModelAttribute MemberDTO dto, Model model) {
		logger.info("회원 수정");
		boolean result = memberService.checkPw(dto.getUserid(), dto.getPasswd());
		if(result) {
			memberService.updateMember(dto);
			return "redirect:/member/list.do";
		}else {
			MemberDTO viewMember = memberService.viewMember(dto.getUserid());
			dto.setJoinDate(viewMember.getJoinDate());
			model.addAttribute("viewMember", dto);
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			return "member/view";
		} 
	}
	
	@RequestMapping("member/delete.do")
	public String delete(@RequestParam String userid, @RequestParam String passwd,
			Model model) {
		logger.info("회원 삭제");
		boolean result = memberService.checkPw(userid, passwd);
		if(result) {
			memberService.deleteMember(userid);
			return "redirect:/member/list.do";
		}else {
			model.addAttribute("viewMember", memberService.viewMember(userid));
			model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
			return "member/view";
		} 
	}
	
	@RequestMapping("member/login.do")
	public String login(Model model) {
		logger.info("로그인 페이지");
		return "member/login";
	}
	
	@RequestMapping("member/loginCheck.do")
	public ModelAndView loginCheck(MemberDTO dto, HttpSession session, ModelAndView mav) {
		logger.info("로그인check");
		String name = memberService.loginCheck(dto);
		if(name != null) {
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", name);
			mav.setViewName("main");
			mav.addObject("message", "success");
			System.out.println("message: success");
			System.out.println("dto.getUserid(): " + dto.getUserid());
		}else {
			System.out.println("message: error");
			mav.setViewName("member/login");
			mav.addObject("message", "error");
			
		}
		return mav;
	}
	
	@RequestMapping("member/logout.do")
	public String logout(HttpSession session) {
		logger.info("로그아웃");
		session.invalidate();
		return "redirect:/member/login.do";
	}
}
