package com.example.spring01.service;

import java.util.List;

import com.example.spring01.model.dto.MemberDTO;

public interface AdminService {
	public List<MemberDTO> memberList();
	public void insertMember(MemberDTO dto);
	public MemberDTO viewMember(String userid);
	public void deleteMember(String userid);
	public void updateMember(MemberDTO dto);
	public boolean checkPw(String userid, String passwd);
	public String loginCheck(MemberDTO dto);
}
