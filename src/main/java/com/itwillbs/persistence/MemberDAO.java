package com.itwillbs.persistence;

import com.itwillbs.domain.MemberVO;

public interface MemberDAO {

	//회원가입 처리
	public void insert(MemberVO vo) throws Exception;
	
	//아이디 중복 체크 
	public int idCheck(MemberVO vo) throws Exception;
	
	//로그인
	public MemberVO login (MemberVO vo) throws Exception;
	
	//로그인 2
	public MemberVO login (String user_id, String user_pw) throws Exception;
}