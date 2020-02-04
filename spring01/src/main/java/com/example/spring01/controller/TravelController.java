package com.example.spring01.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring01.HomeController;
import com.example.spring01.model.dto.ProductDTO;
import com.example.spring01.model.dto.TravelDTO;
import com.example.spring01.service.TravelService;

@Controller
public class TravelController {

	private static final Logger logger = LoggerFactory.getLogger(TravelController.class);
	
	@Inject
	TravelService travelService;
	
	@RequestMapping(value = "/travel.do", method = RequestMethod.GET)
	public ModelAndView travel() {
		logger.info("travel Page");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("travel", "aaa");
		
		return new ModelAndView("travel","map",map);
	}
	
	@RequestMapping(value = "/travel/list.do")
	public String travelList(Model model) {
		logger.info("travelList Page");
		
		List<TravelDTO> travelList = travelService.travelList();
		model.addAttribute("travelList", travelList);
		
		return "travel/travel_list";
	}
	
	@RequestMapping(value = "/travel/writeView.do")
	public String writeView(Model model) {
		logger.info("writeView Page");
		
		return "travel/travel_write";
	}
	
	@RequestMapping(value = "/travel/insert.do")
	public String travelInsert(@ModelAttribute TravelDTO dto) {
		logger.info("travelInsert Page");
		
		travelService.travelInsert(dto);
		
		return "redirect:/travel/list.do";
	}
}
