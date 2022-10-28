package com.itwillbs.service;

import com.itwillbs.domain.MemberVO;

public interface MemberService {


	//회원 가입 처리
	public void insert(MemberVO vo) throws Exception;
	
	//아이디 중복체크
	public int idCheck(MemberVO vo) throws Exception;
	
	// 로그인 
	public MemberVO login(MemberVO vo) throws Exception;
	
	// 로그인2 
	public MemberVO login(String user_id,String user_pw) throws Exception;
		
}

	
	
	
}


