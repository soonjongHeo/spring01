package com.example.spring01.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring01.model.dto.CartDTO;
import com.example.spring01.service.CartService;

@Controller
@RequestMapping("shop/cart/*") // 공통적인 url mapping
public class CartController {

	@Inject
	CartService cartService;

	@RequestMapping("list.do")
	public ModelAndView list(HttpSession session, ModelAndView mav) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 세션변수 확인
		String userId = (String) session.getAttribute("userid");
		//if (userId != null) { // 로그인한 경우
			List<CartDTO> list = cartService.listCart(userId);
			// 장바구니 합계 계산
			int sumMoney = cartService.sumMoney(userId);
			// 배송료 계산
			int fee = sumMoney >= 30000 ? 0 : 2500;
			map.put("sumMoney", sumMoney); // 장바구니 금액 합계
			map.put("fee", fee); // 배송료
			map.put("sum", sumMoney + fee); // 총합계금액

			map.put("list", list); // 맵에 자료 추가
			map.put("count", list.size());
			mav.setViewName("shop/cart_list"); // jsp 페이지 이름
			mav.addObject("map", map); // jsp에 전달할 데이터
			System.out.println("map: " + map);
			return mav;
		//} else { // 로그인하지 않은 경우 userId <- null
		//	return new ModelAndView("member/login", "", null);
		//}
	}

	@RequestMapping("insert.do") // 세부적인 url mapping
	public String insert(HttpSession session, @ModelAttribute CartDTO dto) {
		// 세션에 userId 변수가 존재하는지 확인
		String userId = (String) session.getAttribute("userid");
		System.out.println("userId: " + userId);
		//if (userId == null) { // 로그인 하지 않은 상태
		//	return "redirect:/member/login.do"; // 로그인 페이지로
		//}
		// 장바구니에 insert 처리 후 장바구니 목록으로 이동
		dto.setUserId(userId);
		cartService.insert(dto);
		return "redirect:/shop/cart/list.do";
	}

	// 장바구니 개별 상품 삭제
	@RequestMapping("delete.do")
	public String delete(@RequestParam int cartId
			,HttpSession session) {
		if(session.getAttribute("userid") != null)
			cartService.delete(cartId);
		return "redirect:/shop/cart/list.do";
	}

	@RequestMapping("deleteAll.do")
	public String deleteAll(HttpSession session) {
		// 세션변수 조회(로그인 여부 검사)
		String userId = (String) session.getAttribute("userid");
		if (userId != null) { // 로그인한 상태이면
			// 장바구니를 비우고
			cartService.deleteAll(userId);
		}
		// 장바구니 목록으로 이동
		return "redirect:/shop/cart/list.do";
	}

	@RequestMapping("update.do")
	public String update(@RequestParam int[] amount, @RequestParam int[] cartId, HttpSession session) {
		String userId = (String) session.getAttribute("userid");
		if (userId != null) {
			for (int i = 0; i < cartId.length; i++) {
				if (amount[i] == 0) { //수량이 0이면 레코드 삭제
					cartService.delete(cartId[i]);
				} else { //0이 아니면 수정
					CartDTO dto = new CartDTO();
					dto.setUserId(userId);
					dto.setCartId(cartId[i]);
					dto.setAmount(amount[i]);
					cartService.modifyCart(dto);
				}
			}
		}
		return "redirect:/shop/cart/list.do";
	}
}
