package com.itwillbs.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class MemberDAOTest {
	
	@Inject
	private MemberDAO dao;
	
	
	private static final Logger log = LoggerFactory.getLogger(MemberDAOTest.class);
	
	
	// @Test
	public void daoTest() {
		log.info("@@@@@ dao : "+dao);
	}
	
	
	// 회원 가입 테스트
	// @Test
	public void insertTest() {
		log.info("@@@@@ insertTest() 호출");
		log.info("@@@@@ 테스트 -> DAOImpl");
		
		MemberVO vo = new MemberVO();
		vo.setUser_id("haaland@gmail.com");
		vo.setUser_pw("1234");
		vo.setUser_name("홀란드");
		vo.setUser_phone("010-9999-9999");
		
		try {
			dao.insert(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	// 회원 탈퇴 테스트
	@Test
	public void deleteTest() {
		log.info("@@@@@ deleteTest() 호출");
		
		MemberVO dvo = new MemberVO();
		dvo.setUser_id("cc");
		dvo.setUser_pw("1234");
		
		int result = dao.deleteMember(dvo);
		
		if(result == 1) {
			log.info("삭제 완료");
		}else {
			log.info("삭제 실패");
		}
	}
	
}
