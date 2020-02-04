package com.example.spring01.model.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.spring01.model.dao.CartDAO;
import com.example.spring01.model.dto.CartDTO;

@Repository
public class CartDAOImpl implements CartDAO {

	@Inject
	SqlSession sqlSession;
	//상품별 장바구니 금액 통계
	@Override
	public List<CartDTO> cartMoney() {
		return sqlSession.selectList("cart.cart_money"); 
	}
	@Override
	public void insert(CartDTO dto) {
		sqlSession.insert("cart.insert", dto); 
	}

	//장바구니 목록
	@Override
	public List<CartDTO> listCart(String userId) {
		return sqlSession.selectList("cart.listCart", userId); 
	}

	//장바구니 개별 상품 삭제
	@Override
	public void delete(int cartId) {
		sqlSession.delete("cart.delete", cartId); 
	}
//장바구니 비우기
	@Override
	public void deleteAll(String userId) {
		sqlSession.delete("cart.deleteAll", userId); 
	}

	@Override
	public void update(int cartId) {
		// TODO Auto-generated method stub

	}
//장바구니 금액 합계 계산
	@Override
	public int sumMoney(String userId) { 
		return sqlSession.selectOne("cart.sumMoney", userId); 
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
		sqlSession.update("cart.modifyCart", dto); 
	}

}









