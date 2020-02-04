package com.example.spring01.common.common.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.spring01.common.dao.AbstractDAO;

@Repository("commonDAO")
public class CommonDAO extends AbstractDAO {
	
	@SuppressWarnings("unchecked") 
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception{
		return (Map<String, Object>)selectOne("common.selectFileInfo", map); 
	}
 
}
