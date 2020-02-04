package com.example.spring01.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.spring01.common.common.CommandMap;
import com.example.spring01.common.util.FileUtils;
import com.example.spring01.model.dao.BoardDAO;

@Service("boardService")
public class BoardServiceImpl implements BoardService{

	private static final Logger logger = 
			LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Resource(name="fileUtils") 
	private FileUtils fileUtils;
 
	@Resource(name="boardDao") 
	private BoardDAO boardDao; 
	
	@Override
	public Map<String, Object> boardList(Map<String, Object> map) throws Exception {
		return boardDao.boardList(map); 
	}
	@Override 
	public void insertBoard(Map<String, Object> map, HttpServletRequest request) throws Exception { 
		boardDao.insertBoard(map);
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(map, request); 
		for(int i=0; i<list.size(); i++){ 
			boardDao.insertFile(list.get(i)); 
		}
 
	}
	@Override
	public Map<String, Object> selectBoardDetail(Map<String, Object> map, HttpSession session) throws Exception {
		updateHitCnt(map, session);
		Map<String, Object> resultMap = new HashMap<String,Object>();
		Map<String, Object> tempMap = boardDao.selectBoardDetail(map);
		resultMap.put("map", tempMap); 
		
		List<Map<String,Object>> list = boardDao.selectFileList(map); 
		resultMap.put("list", list);
 
		return resultMap;
	}
	@Override
	public void updateBoard(Map<String, Object> map, HttpServletRequest request) throws Exception{
		boardDao.updateBoard(map);
		System.out.println("map: " + map);
		boardDao.deleteFileList(map);
		List<Map<String,Object>> list = fileUtils.parseUpdateFileInfo(map, request);
		System.out.println("list: " + list);
		Map<String,Object> tempMap = null;
		for(int i=0; i<list.size(); i++){
			tempMap = list.get(i);
			if(tempMap.get("IS_NEW").equals("Y")){
				boardDao.insertFile(tempMap);
			}
			else{
				boardDao.updateFile(tempMap);
			}
		}
	}
	@Override
	public void deleteBoard(Map<String, Object> map) throws Exception {
		boardDao.deleteBoard(map);
	}
	@Override
    public void updateHitCnt(Map<String, Object> map, HttpSession session) throws Exception {
        long update_time = 0;
        // 세션에 저장된 조회시간 검색
        // 최초로 조회할 경우 세션에 저장된 값이 없기 때문에 if문은 실행X
        if(session.getAttribute("update_time_"+map.get("IDX")) != null){
                                // 세션에서 읽어오기
            update_time = (Integer)session.getAttribute("update_time_" + map.get("IDX"));
        }else {
        	// 시스템의 현재시간을 current_time에 저장
        	long current_time = System.currentTimeMillis();
        	// 일정시간이 경과 후 조회수 증가 처리 24*60*60*1000(24시간)
        	// 시스템현재시간 - 열람시간 > 일정시간(조회수 증가가 가능하도록 지정한 시간)
        	if(current_time - update_time > 5*1000){
        		boardDao.updateHitCnt(map);
        		// 세션에 시간을 저장 : "update_time_"+bno는 다른변수와 중복되지 않게 명명한 것
        		session.setAttribute("update_time_"+map.get("IDX"), current_time);
        	}
        }
    }

}
