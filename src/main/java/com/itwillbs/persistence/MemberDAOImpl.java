package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;


import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	private static final Logger log = 
			LoggerFactory.getLogger(MemberDAOImpl.class);
	private static final String NAMESPACE = "com.itwillbs.mapper.MemberMapper";
	
	private final SqlSession sqlSession;

	@Inject
	public MemberDAOImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	} 

	// 회원가입 처리
	@Override
	public void insert(MemberVO vo) throws Exception {
		sqlSession.insert(NAMESPACE+".insert", vo);
	}

	//아이디 중복 체크
	@Override
	public int idCheck(MemberVO vo) throws Exception {
		return sqlSession.selectOne(NAMESPACE+".idCheck", vo);
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

	@Override
	public MemberVO getMember(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateMember(MemberVO uvo) {
		
		log.info(" 테스트 -> updateMember(MemberVO uvo) 호출 ");
		
		int result = sqlSession.update(NAMESPACE+".updateMember",uvo);
		
		log.info(" 회원 정보 수정 완료 ");
		// result => 0 (수정x),1 (수정o)	
		log.info(" updateMember -> 테스트 호출 ");
		
		return result;
	}

	@Override
	public Integer deleteMember(MemberVO dvo) {
		// mapper - sql 호출
		return sqlSession.delete(NAMESPACE+".deleteMember",dvo);
	}

	@Override
	public List<MemberVO> getMemberList() {
		// TODO Auto-generated method stub
		return null;
	}


}