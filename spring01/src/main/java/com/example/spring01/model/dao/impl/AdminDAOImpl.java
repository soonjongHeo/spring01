package com.example.spring01.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.spring01.model.dao.AdminDAO;
import com.example.spring01.model.dao.MemberDAO;
import com.example.spring01.model.dto.MemberDTO;

@Repository
public class AdminDAOImpl implements AdminDAO{

	private static final Logger logger = 
			LoggerFactory.getLogger(AdminDAOImpl.class);
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<MemberDTO> memberList() {
		logger.info("memberList called");
		return sqlSession.selectList("member.memberList");
	}

	@Override
	public void insertMember(MemberDTO dto) {
		logger.info("insertMember called");
		sqlSession.insert("member.insertMember", dto);
	}

	@Override
	public MemberDTO viewMember(String userid) {
		logger.info("viewMember called");
		return sqlSession.selectOne("member.viewMember", userid);
	}

	@Override
	public void deleteMember(String userid) {
		logger.info("deleteMember called");
		sqlSession.insert("member.deleteMember", userid);
	}

	@Override
	public void updateMember(MemberDTO dto) {
		logger.info("updateMember called");
		sqlSession.insert("member.updateMember", dto);
	}

	@Override
	public boolean checkPw(String userid, String passwd) {
		logger.info("checkPw called");
		boolean result = false;
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("passwd", passwd);
		int count = sqlSession.selectOne("member.checkPw", map);
		if(count == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public String loginCheck(MemberDTO dto) {
		logger.info("Admin loginCheck called");
		return sqlSession.selectOne("admin.loginCheck", dto);
	}

}
