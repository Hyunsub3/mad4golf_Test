package com.itwillbs.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;
import com.itwillbs.persistence.ProductDAO;
import com.itwillbs.persistence.ProductDAOImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class BoardDAOTest {
	
	private static final Logger log 
	       = LoggerFactory.getLogger(BoardDAOTest.class);
	
	// DAO 객체필요(의존관계)
	@Autowired
	private ProductDAO dao; //의존관계 주입
	
	//@Test
	public void 테스트확인() {
		log.info(" 테스트확인() @@@@@@@@@@@@@@  ");
	}
	
	//@Test
	public void 페이징처리완료() {
		// DAOImpl - listPage()호출
		try {
			for(BoardVO vo :dao.listPage(5)) {
				log.info(vo.getBoard_num()+":"+vo.getTitle());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void pageVoObjectTest() {
		log.info(" pageVoObjectTest() 호출 ");
		
		PageVO vo = new PageVO(); // 1페이지, 10개씩
				
		try {
			System.out.println(dao.listPage(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
