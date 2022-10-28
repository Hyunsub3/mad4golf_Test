package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;
import com.itwillbs.service.BoardServiceImpl;

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	public static final Logger log
	= LoggerFactory.getLogger(BoardServiceImpl.class);
	

	@Autowired
	private SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.itwillbs.mapper.BoardMapper";
			
	
	//----------------------------------------------------------------------
	
	
	
	@Override
	public void boardWrite(BoardVO vo) throws Exception{
		log.info(" 3. DAO - insertBoard(vo) ");
		
		int result = sqlSession.insert(NAMESPACE+".boardWrite", vo);
		
		if(result > 0) {
			log.info(" 4. DB - 글 삽입 완료 ");
		}
		
	}
	
	
	
	
	//----------------------------------------------------------------------

	
	@Override
	public List<BoardVO> listBoardAll() throws Exception {
		log.info(" 3. DAO - listBoardAll() " );
		
		List<BoardVO> boardListAll = sqlSession.selectList(NAMESPACE + ".listBoardAll");
		
		return boardListAll;
	}
	
	
	//----------------------------------------------------------------------

	
	


	
	@Override
	public List<BoardVO> listPage(Integer page) throws Exception {
		log.info(" 3. DAO listPage(page) 호출 " );

		if(page <= 0) {
			page = 1;
			
		}
		
		int pageSize = 30;
		
		//1-0, 2-10, 3-20, 4-30,....
//		page = (page - 1)*10;
		page = (page - 1)*pageSize;
		
		
		Map<String, Object> pageObj = new HashMap<String, Object>();
		pageObj.put("page", page);
		pageObj.put("pageSize", pageSize);
		
		
		return sqlSession.selectList(NAMESPACE+".listPage2",pageObj);
	}
	
	

	

	//----------------------------------------------------------------------

	
	
	//PageVO 안에 페이징 처리 관련 정보들 담아오기
	@Override
	public List<BoardVO> listPage(PageVO vo) throws Exception {
		
		log.info(" 3. DAO - listPage(vo) 호출 ");
		
		
		return sqlSession.selectList(NAMESPACE+".listPage3",vo);
	}
	
}
