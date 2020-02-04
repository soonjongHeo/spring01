package com.example.spring01.model.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.spring01.model.dao.ProductDAO;
import com.example.spring01.model.dto.ProductDTO;

@Repository
public class ProductDAOImpl implements ProductDAO {

	private static final Logger logger = 
			LoggerFactory.getLogger(ProductDAOImpl.class);
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<ProductDTO> productList() {
		logger.info("productList called...");
		return sqlSession.selectList("product.productList");
	}

	@Override
	public ProductDTO productDetail(int productId) {
		logger.info("productDetail called");
		return sqlSession.selectOne("product.productDetail", productId);
	}

	@Override
	public void productInsert(ProductDTO dto) {
		logger.info("productInsert called");
		sqlSession.insert("product.productInsert", dto);
	}

	@Override
	public void productUpdate(ProductDTO dto) {
		logger.info("productUpdate called");
		sqlSession.insert("product.productUpdate", dto);
	}

	@Override
	public void productDelete(int productId) {
		logger.info("productDelete called");
		sqlSession.insert("product.productDelete", productId);
	}

	@Override
	public String fileInfo(int productId) {
		logger.info("fileInfo called");
		return sqlSession.selectOne("product.fileInfo", productId);
	}

}
