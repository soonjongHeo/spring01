package com.example.spring01.common.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.example.spring01.common.common.Pager;

public class AbstractDAO {
	private static final Logger logger = LoggerFactory.getLogger(AbstractDAO.class);
	
	@Inject
	private SqlSession sqlSession;
	
	protected void printQueryId(String queryId) {
		logger.info("\t QueryId  \t:  " + queryId);
	}
	
	public Object insert(String queryId, Object params){
		printQueryId(queryId);
		return sqlSession.insert(queryId, params);
	}
	
	public Object update(String queryId, Object params){
		printQueryId(queryId);
		return sqlSession.update(queryId, params);
	}
	
	public Object delete(String queryId, Object params){
		printQueryId(queryId);
		return sqlSession.delete(queryId, params);
	}
	
	public Object selectOne(String queryId){
		printQueryId(queryId);
		return sqlSession.selectOne(queryId);
	}
	
	public Object selectOne(String queryId, Object params){
		printQueryId(queryId);
		return sqlSession.selectOne(queryId, params);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectList(String queryId){
		printQueryId(queryId);
		return sqlSession.selectList(queryId);
	}
	
	@SuppressWarnings("rawtypes")
	public List selectList(String queryId, Object params){
		printQueryId(queryId);
		return sqlSession.selectList(queryId,params);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map selectPagingList(String queryId, Object params){
		printQueryId(queryId);
		
		Map<String,Object> map = (Map<String,Object>)params;
		Pager paginationInfo = null;
		
		if(map.containsKey("currentPageNo") == false || StringUtils.isEmpty(map.get("currentPageNo")) == true)
			map.put("currentPageNo","1");
		System.out.println("currentPageNo: " + Integer.parseInt(map.get("currentPageNo").toString()));
		paginationInfo = new Pager();
		paginationInfo.setCurrentPageNo(Integer.parseInt(map.get("currentPageNo").toString()));
		if(map.containsKey("PAGE_ROW") == false || StringUtils.isEmpty(map.get("PAGE_ROW")) == true){
			paginationInfo.setRecordCountPerPage(10);
		}
		else{
			paginationInfo.setRecordCountPerPage(Integer.parseInt(map.get("PAGE_ROW").toString()));
		}
		paginationInfo.setPageSize(10);
		
		int start = paginationInfo.getPageBegin();
		int end = paginationInfo.getPageEnd();
		map.put("START",start);
		map.put("END",end);
		
		params = map;
		
		Map<String,Object> returnMap = new HashMap<String,Object>();
		List<Map<String,Object>> list = sqlSession.selectList(queryId,params);
		
		if(list.size() == 0){
			map = new HashMap<String,Object>();
			map.put("TOTAL_COUNT",0);  
			list.add(map);
			
			if(paginationInfo != null){
				paginationInfo.setTotalRecordCount(0);
				returnMap.put("pager", paginationInfo);
			}
		}
		else{
			if(paginationInfo != null){
				paginationInfo.setTotalRecordCount(Integer.parseInt(list.get(0).get("TOTAL_COUNT").toString()));
 
				returnMap.put("pager", paginationInfo);
			}
		}
		returnMap.put("list", list);
		return returnMap;
	}

}
