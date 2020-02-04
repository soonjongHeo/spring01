package com.example.spring01.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

import com.example.spring01.common.common.CommandMap;
import com.example.spring01.model.dto.MemberDTO;
import com.example.spring01.model.dto.ProductDTO;
import com.example.spring01.service.ProductService;

@Controller
@RequestMapping("shop/")
public class ProductController {

	private static final Logger logger = 
			LoggerFactory.getLogger(ProductController.class);
	
	@Inject
	ProductService productService;
	
	@RequestMapping("product/list.do")
	public String list(Model model)	 {
		List<ProductDTO> list = productService.productList();
		logger.info("상품 목록: " + list);
		model.addAttribute("list", list);
		return "product/product_list";
	}
	
	@RequestMapping("product/write.do")
	public String write() {
		logger.info("상품 등록페이지");
		return "product/product_write";
	}
	
	@RequestMapping("product/insert.do")
	public String insert(ProductDTO dto) {
		logger.info("상품 등록");
		String filename = "-";
		if(!dto.getFile1().isEmpty()) { //첨부파일이 존재하면
			filename = dto.getFile1().getOriginalFilename();
			String path = 
					"D:\\upload\\images\\";
			new File(path).mkdir();
			try {
				dto.getFile1().transferTo(new File(path+filename));
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		dto.setPictureUrl(filename);
		productService.productInsert(dto);
		return "redirect:/shop/product/list.do";
	}
	
	@RequestMapping("product/detail.do")
	public String detail(@RequestParam int productId, Model model) {
		logger.info("상품 상세");
		ProductDTO detail = productService.productDetail(productId);
		model.addAttribute("detail", detail);
		return "product/product_detail";
	}
	
	@RequestMapping(value="product/updateP.do")
	public ModelAndView updateP(@RequestParam int productId, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView("/product/product_update");
		
		ProductDTO detail = productService.productDetail(productId);
		mv.addObject("detail", detail);
		
		return mv;
	}
	
	@RequestMapping("product/update.do")
	public String update(@ModelAttribute ProductDTO dto, Model model) {
		logger.info("상품 수정");
		String filename = "-";
		if(!dto.getFile1().isEmpty()) { //첨부파일이 존재하면
			filename = dto.getFile1().getOriginalFilename();
			String path = "D:\\upload\\images\\";
			new File(path).mkdir();
			dto.setPictureUrl(filename);
			try {
				dto.getFile1().transferTo(new File(path+filename));
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}else {
			ProductDTO dto2 = productService.productDetail(dto.getProductId());
			dto.setPictureUrl(dto2.getPictureUrl());
		}
		productService.productUpdate(dto);
		return "redirect:/shop/product/list.do";
	}
	
	@RequestMapping("product/delete.do")
	public String delete(@RequestParam int productId, Model model) {
		logger.info("상품 삭제");
		String fileName = productService.fileInfo(productId);
		if(fileName != null && !fileName.equals("-")) {
			String path = "D:\\upload\\images\\";
			File f = new File(path+fileName);
			if(f.exists()) {
				f.delete();
			}
		}
		productService.productDelete(productId);
		return "redirect:/shop/product/list.do";
	}
}