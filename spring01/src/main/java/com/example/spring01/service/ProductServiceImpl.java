package com.example.spring01.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring01.model.dao.ProductDAO;
import com.example.spring01.model.dto.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService {

	@Inject 
	ProductDAO productDao;
	
	@Override
	public List<ProductDTO> productList() {
		return productDao.productList();
	}

	@Override
	public ProductDTO productDetail(int productId) {
		return productDao.productDetail(productId);
	}

	@Override
	public void productInsert(ProductDTO dto) {
		productDao.productInsert(dto);
	}

	@Override
	public void productUpdate(ProductDTO dto) {
		productDao.productUpdate(dto);
	}

	@Override
	public void productDelete(int productId) {
		productDao.productDelete(productId);
	}

	@Override
	public String fileInfo(int productId) {
		return productDao.fileInfo(productId);
	}

}
