package com.example.spring01.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring01.HomeController;
import com.example.spring01.model.dto.ProductDTO;

@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "main";
	}
	
	@RequestMapping(value = "/product.do", method = RequestMethod.GET)
	public ModelAndView product() {
		logger.info("product Page");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("product", "aaa");
		
		return new ModelAndView("product","map",map);
	}
	
	@RequestMapping(value = "/googleMap.do", method = RequestMethod.GET)
	public ModelAndView googleMap() {
		logger.info("googleMap Page");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("googleMap", "aaa");
		
		return new ModelAndView("googleMap","map",map);
	}
	
	
}
