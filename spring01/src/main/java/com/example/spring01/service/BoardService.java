package com.example.spring01.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.spring01.common.common.CommandMap;

public interface BoardService {
	Map<String, Object> boardList(Map<String, Object> map) throws Exception; 
	void insertBoard(Map<String, Object> map, HttpServletRequest request) throws Exception;
	Map<String, Object> selectBoardDetail(Map<String, Object> map, HttpSession session) throws Exception;
	void updateBoard(Map<String, Object> map, HttpServletRequest request) throws Exception; 
	void deleteBoard(Map<String, Object> map) throws Exception; 
	void updateHitCnt(Map<String, Object> map, HttpSession session) throws Exception;
}
