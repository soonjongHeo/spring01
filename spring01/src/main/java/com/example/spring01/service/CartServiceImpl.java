package com.example.spring01.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring01.model.dao.CartDAO;
import com.example.spring01.model.dto.CartDTO;

@Service //service bean으로 등록
public class CartServiceImpl implements CartService {

	@Inject //의존관계 주입
	CartDAO cartDao;
	
//상품별 장바구니 금액 통계	
	@Override
	public List<CartDTO> cartMoney() {
		return cartDao.cartMoney();
	}
	@Override
	public void insert(CartDTO dto) {
		cartDao.insert(dto); 
	}

	@Override
	public List<CartDTO> listCart(String userId) {
		return cartDao.listCart(userId); 
	}

	@Override
	public void delete(int cartId) {
		cartDao.delete(cartId); 
	}
//장바구니 비우기
	@Override
	public void deleteAll(String userId) {
		cartDao.deleteAll(userId); 
	}

	@Override
	public void update(int cartId) {
		// TODO Auto-generated method stub

	}

	@Override
	public int sumMoney(String userId) {
		return cartDao.sumMoney(userId);
	}

	@Override
	public int countCart(String userId, int productId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateCart(CartDTO dto) {
		// TODO Auto-generated method stub

	}
//장바구니 수정
	@Override
	public void modifyCart(CartDTO dto) {
		cartDao.modifyCart(dto); 
	}

}








