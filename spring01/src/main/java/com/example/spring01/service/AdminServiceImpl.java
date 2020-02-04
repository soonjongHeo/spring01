package com.example.spring01.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring01.model.dao.AdminDAO;
import com.example.spring01.model.dao.MemberDAO;
import com.example.spring01.model.dto.MemberDTO;

@Service
public class AdminServiceImpl implements AdminService {

	@Inject
	AdminDAO adminDao;
	
	@Override
	public List<MemberDTO> memberList() {
		return adminDao.memberList();
	}

	@Override
	public void insertMember(MemberDTO dto) {
		adminDao.insertMember(dto);
	}

	@Override
	public MemberDTO viewMember(String userid) {
		return adminDao.viewMember(userid);
	}

	@Override
	public void deleteMember(String userid) {
		adminDao.deleteMember(userid);
	}

	@Override
	public void updateMember(MemberDTO dto) {
		adminDao.updateMember(dto);
	}

	@Override
	public boolean checkPw(String userid, String passwd) {
		return adminDao.checkPw(userid, passwd);
	}

	@Override
	public String loginCheck(MemberDTO dto) {
		return adminDao.loginCheck(dto);
	}

}
