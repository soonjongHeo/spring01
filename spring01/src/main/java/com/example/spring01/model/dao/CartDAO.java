package com.example.spring01.model.dao;

import java.util.List;

import com.example.spring01.model.dto.CartDTO;

public interface CartDAO {
	public List<CartDTO> cartMoney();
	public void insert(CartDTO dto);
	public List<CartDTO> listCart(String userId);
	public void delete(int cartId);
	public void deleteAll(String userId);
	public void update(int cartId);
	public int sumMoney(String userId);
	public int countCart(String userId, int productId);
	public void updateCart(CartDTO dto);
	public void modifyCart(CartDTO dto);
}




