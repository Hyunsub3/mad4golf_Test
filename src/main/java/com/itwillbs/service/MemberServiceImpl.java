package com.itwillbs.service;

import javax.inject.Inject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{

	private static final Logger logger 
			= LoggerFactory.getLogger(MemberServiceImpl.class);


	private final MemberDAO dao;

	@Inject
	public MemberServiceImpl(MemberDAO dao) {
		this.dao= dao;
	}

	//회원 가입 처리.
	@Override
	public void insert(MemberVO vo) throws Exception {
		logger.info("MemberServiceImpl - insert() 호출");
		dao.insert(vo);
		logger.info("DAO 동작 완료! 서비스 -> 컨트롤러");

	}
	//아이디 중복 체크
	@Override
	public int idCheck(MemberVO vo) throws Exception {
		return dao.idCheck(vo);
	}

	@Override
	public MemberVO getMember(String id) {
		//메서드 호출
		return dao.getMember(id);
	}

	@Override
	public Integer updateMember(MemberVO uvo) {
		//메서드 호출
		return dao.updateMember(uvo);
	}

	@Override
	public MemberVO loginMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO loginMember(String userid, String userpw) {
		// TODO Auto-generated method stub
		return null;
	}



}