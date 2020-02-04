package com.example.spring01.model.dao;

import java.util.List;
import java.util.Map;

import com.example.spring01.common.common.CommandMap;

public interface BoardDAO {
	Map<String, Object> boardList(Map<String, Object> map) throws Exception;
	void insertBoard(Map<String, Object> map) throws Exception;
	void updateHitCnt(Map<String, Object> map) throws Exception;
	Map<String, Object> selectBoardDetail(Map<String, Object> map) throws Exception;
	void updateBoard(Map<String, Object> map) throws Exception;
	void deleteBoard(Map<String, Object> map) throws Exception;
	void insertFile(Map<String, Object> map) throws Exception;
	List<Map<String, Object>> selectFileList(Map<String, Object> map) throws Exception;
	void deleteFileList(Map<String, Object> map) throws Exception;
	void updateFile(Map<String, Object> map) throws Exception;
}
