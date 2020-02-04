package com.example.spring01.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.spring01.common.common.CommandMap;
import com.example.spring01.common.dao.AbstractDAO;
import com.example.spring01.model.dao.BoardDAO;

@Repository("boardDao")
public class BoardDAOImpl extends AbstractDAO implements BoardDAO {

	@SuppressWarnings("unchecked") 
	public Map<String, Object> boardList(Map<String, Object> map) throws Exception {
		return (Map<String,Object>)selectPagingList("board.boardList", map); 
	}
	public void insertBoard(Map<String, Object> map) throws Exception{
		insert("board.insertBoard", map);
	}
	public void updateHitCnt(Map<String, Object> map) throws Exception{
		update("board.updateHitCnt", map);
	}
	@SuppressWarnings("unchecked")
	public Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception{
		return (Map<String, Object>) selectOne("board.selectBoardDetail", map);
	}
	public void updateBoard(Map<String, Object> map) throws Exception{ 
		update("board.updateBoard", map); 
	} 
	public void deleteBoard(Map<String, Object> map) throws Exception{
		update("board.deleteBoard", map);
	}
	public void insertFile(Map<String, Object> map) throws Exception{ 
		insert("board.insertFile", map); 
	}
	public List<Map<String, Object>> selectFileList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>)selectList("board.selectFileList", map);
	}
	public void deleteFileList(Map<String, Object> map) throws Exception{
		update("board.deleteFileList", map);
	}
	public void updateFile(Map<String, Object> map) throws Exception{
		update("board.updateFile", map);
	}


}
