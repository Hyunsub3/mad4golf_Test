package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;


import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberVO;
import com.mysql.cj.log.Log;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	

	private static final Logger log 
		= LoggerFactory.getLogger(MemberDAOImpl.class);
	
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

	// 로그인
	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		log.info("login(vo) 호출"); 
		
		MemberVO resultVO 
		   = sqlSession.selectOne(NAMESPACE+".login",vo);
		
		// mapper에서 쿼리 실행 결과 저장해서 리턴
		
		return resultVO;
	}

	@Override
	public MemberVO login(String user_id, String user_pw) throws Exception {
		log.info("login(userid,userpw) 호출");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//paramMap.put("컬럼명", 데이터값);
		paramMap.put("userid", user_id);
		paramMap.put("userpw", user_pw);
		
		return sqlSession.selectOne(NAMESPACE+".login",paramMap);
	}
	
	

		
}