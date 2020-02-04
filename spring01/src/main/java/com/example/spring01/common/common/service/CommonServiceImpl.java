package com.example.spring01.common.common.service;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.spring01.common.common.controller.CommonController;
import com.example.spring01.common.common.dao.CommonDAO;


@Service("commonService")
public class CommonServiceImpl implements CommonService{
	private static final Logger logger = 
			LoggerFactory.getLogger(CommonServiceImpl.class);
	
	@Resource(name="commonDAO")
	CommonDAO commonDAO;
	
	@Override 
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception { 
		return commonDAO.selectFileInfo(map); 
	}
 
}

