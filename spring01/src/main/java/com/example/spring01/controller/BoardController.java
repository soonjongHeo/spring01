package com.example.spring01.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring01.common.common.Pager;
import com.example.spring01.common.common.CommandMap;
import com.example.spring01.service.BoardService;

@Controller
public class BoardController {
	private static final Logger logger = 
			LoggerFactory.getLogger(BoardController.class);
	
	@Resource(name="boardService")
	private BoardService boardService;
	
	@RequestMapping(value="board/list.do")
    public ModelAndView list(CommandMap commandMap
    		, @RequestParam(defaultValue="1") int currentPageNo
    		, @RequestParam(defaultValue="all") String searchOption
    		, @RequestParam(defaultValue="") String keyword) throws Exception{
		logger.info("게시판 목록");
    	ModelAndView mv = new ModelAndView("board/board_list");
    	
    	commandMap.put("searchOption",searchOption);
    	commandMap.put("keyword",keyword);
    	commandMap.put("currentPageNo",currentPageNo);
    	System.out.println("searchOption: " + commandMap.get("searchOption").toString());
    	Map<String,Object> map = boardService.boardList(commandMap.getMap());
    	mv.addObject("map", map);
    	return mv;
    }
	
	@RequestMapping(value="board/write.do")
	public ModelAndView write(CommandMap commandMap) throws Exception{
		logger.info("게시판 등록페이지");
		ModelAndView mv = new ModelAndView("board/board_write");
		return mv;
	}
	
	@RequestMapping(value="board/write2.do")
	public ModelAndView write2(CommandMap commandMap) throws Exception{
		logger.info("게시판 등록페이지2");
		ModelAndView mv = new ModelAndView("board/board_write2");
		return mv;
	}
	
	@RequestMapping("board/insert.do")
	public ModelAndView insert(CommandMap commandMap, HttpServletRequest request) throws Exception{
		logger.info("게시판 등록");
		ModelAndView mv = new ModelAndView("redirect:/board/list.do");
		boardService.insertBoard(commandMap.getMap(), request);
		return mv;
	}
	
	@RequestMapping(value="/board/detail.do")
	public ModelAndView detail(CommandMap commandMap, HttpSession session) throws Exception{
		logger.info("게시판 상세");
		ModelAndView mv = new ModelAndView("/board/board_detail");
		
		Map<String,Object> map = boardService.selectBoardDetail(commandMap.getMap(), session);
		mv.addObject("map", map.get("map"));
		mv.addObject("list", map.get("list"));
		
		return mv;
	}
	
	@RequestMapping(value="/board/updateP.do")
	public ModelAndView updateP(CommandMap commandMap, HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView("/board/board_update");
		
		Map<String,Object> map = boardService.selectBoardDetail(commandMap.getMap(), session);
		mv.addObject("map", map.get("map"));
		mv.addObject("list", map.get("list")); 
		
		return mv;
	}

	@RequestMapping(value="/board/update.do")
	public ModelAndView update(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/board/detail.do");
		boardService.updateBoard(commandMap.getMap(), request);
		
		mv.addObject("IDX", commandMap.get("IDX"));
		return mv;
	}
	
	@RequestMapping(value="/board/delete.do")
	public ModelAndView delete(CommandMap commandMap) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/board/list.do");
		
		boardService.deleteBoard(commandMap.getMap());
		
		return mv;
	}


	
}
