package com.itwillbs.service;

import com.itwillbs.domain.MemberVO;

public interface MemberService {

	//회원 가입 처리
	public void insert(MemberVO vo) throws Exception;

	//아이디 중복체크
	public int idCheck(MemberVO vo) throws Exception;

	// 로그인 동작
	public MemberVO loginMember(MemberVO vo);

	// 로그인 동작2 (메서드 오버로딩)
	public MemberVO loginMember(String userid, String userpw);

	// 회원정보 조회
	public MemberVO getMember(String id);

	// 회원정보 수정
	public Integer updateMember(MemberVO uvo);
	
	// 회원 탈퇴
	public Integer deleteMember(MemberVO dvo);


}