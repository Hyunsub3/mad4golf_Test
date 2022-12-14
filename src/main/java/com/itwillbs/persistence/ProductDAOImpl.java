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
import com.itwillbs.domain.ProductVO;

@Repository
public class ProductDAOImpl implements ProductDAO {

	private static final Logger log 
	    = LoggerFactory.getLogger(ProductDAOImpl.class);
	
	// SqlSession객체 주입(DI)
	@Autowired
	private SqlSession sqlSession;

	private static final String NAMESPACE = "com.itwillbs.mapper.ProductMapper";

	@Override
	public List<ProductVO> listAll(ProductVO vo) throws Exception {
		log.info("listAll() 호출");
		
		// DB - 모든정보 가져오기(SQL/mapper 호출)
		List<ProductVO> productList;
		
		if(vo.getGender() == 0) { //성별없음:0 남자:1 여자:2
			productList = sqlSession.selectList(NAMESPACE + ".listAll", vo);
			log.info("Mapper - listAll 호출");
		} else {
			productList = sqlSession.selectList(NAMESPACE + ".listAll2", vo);			
			log.info("Mapper - listAll2 호출");
		}
		
		log.info("상품 개수 : "+ productList.size() + "개");
		
		return productList;
	}
	
	@Override
	public void insertProduct(ProductVO vo) throws Exception{
		log.info("insertProduct(vo) 호출");
		
		// SQL 실행 - SqlSession 객체 (디비연결, Mybatis, 매퍼, 자원해제)
		// 상품 등록 (Insert)
		int result = sqlSession.insert(NAMESPACE + ".insert", vo);
		
		if(result > 0)
			log.info("상품 등록 완료!");
	}

	@Override
	public BoardVO getBoard(Integer bno) throws Exception {
		log.info("getBoard(Integer bno) 호출");
		
		BoardVO vo = sqlSession.selectOne(NAMESPACE + ".read",bno);
		
		return vo;
		
		//return sqlSession.selectOne(NAMESPACE + ".read",bno); 
	}

	@Override
	public void updateReadCount(Integer bno) throws Exception {
		log.info(" updateReadCount(Integer bno) 호출 ");
		
		// SQL - mapper 쿼리구문 호출
		sqlSession.update(NAMESPACE + ".updateReadCnt",bno);
		
	}

	
	@Override
	public Integer updateBoard(BoardVO vo) throws Exception {
		log.info(" updateBoard(BoardVO vo) 호출 ");
		
		int cnt = sqlSession.update(NAMESPACE + ".updateBoard",vo);		
		
		return cnt;
	}

	@Override
	public Integer deleteBoard(Integer bno) throws Exception {
		log.info(" deleteBoard(bno) 호출 ");
		
		return sqlSession.delete(NAMESPACE + ".remove",bno);
	}

	
	@Override
	public List<BoardVO> listPage(Integer page) throws Exception {
		log.info("listPage(Integer page) 호출");
		
		if(page <= 0) {
			page = 1;
		}
		int pageSize = 30;
		// 1-0, 2-10, 3-20,4-30,.....
		//page = (page - 1) * 10;
		page = (page - 1) * pageSize;
		
		Map<String, Object> pageObj = new HashMap<String, Object>();
		pageObj.put("page", page);
		pageObj.put("pageSize", pageSize);

		
		return sqlSession.selectList(NAMESPACE + ".listPage2", pageObj);
	}

	@Override
	public List<ProductVO> listPage(PageVO vo) throws Exception {
		log.info(" listProductPage(ProductVO vo) 호출 ");
		
		return sqlSession.selectList(NAMESPACE + ".listPage3",vo);		
	}
	
	
	
	
	

}
