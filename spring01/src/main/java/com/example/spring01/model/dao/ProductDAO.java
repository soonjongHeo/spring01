package com.example.spring01.model.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.spring01.model.dto.ProductDTO;

public interface ProductDAO {
	public List<ProductDTO> productList();
	public ProductDTO productDetail(int productId);
	public void productInsert(ProductDTO dto);
	public void productUpdate(ProductDTO dto); 
	public void productDelete(int productId);
	String fileInfo(int productId);
	public List<ProductDTO> jsonProductList(ProductDTO productDTO);
}
