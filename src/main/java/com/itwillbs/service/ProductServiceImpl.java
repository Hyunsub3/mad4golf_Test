package com.itwillbs.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;
import com.itwillbs.domain.ProductVO;
import com.itwillbs.persistence.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

	// BoardDAO 객체 주입(DI)
	@Autowired
	private ProductDAO dao;

	@Override
	public void boardWrite(BoardVO vo) throws Exception {
		log.info(" boardWrite(vo) 호출 ");
		// DAO - insertBoard(vo) 호출
		dao.insertBoard(vo);

		log.info(" boardWrite(vo) 완료 ");
	}

	@Override
	public List<ProductVO> getProductListAll(ProductVO vo) throws Exception {
		log.info("getProductListAll() 호출");

		List<ProductVO> productList = dao.listAll(vo);

		return productList;
	}

	@Override
	public BoardVO getBoard(Integer bno) throws Exception {
		log.info("getBoard(Integer bno) 호출 ");

		BoardVO vo = dao.getBoard(bno);

		return vo;
		//	return dao.getBoard(bno);
	}

	@Override
	public void updateReadCount(Integer bno) throws Exception {
		log.info("updateReadCount(bno) 호출");
		
		// DAO - updateReadCount(BNO)
		dao.updateReadCount(bno);
		
	}

	@Override
	public Integer updateBoard(BoardVO vo) throws Exception {
		log.info(" updateBoard(vo) ");
		
		int cnt = dao.updateBoard(vo);
		
		return cnt;
	}

	@Override
	public Integer deleteBoard(Integer bno) throws Exception {
		log.info(" deleteBoard(bno) 호출 ");
		
		return dao.deleteBoard(bno);
	}

	@Override
	public List<BoardVO> listPage(PageVO vo) throws Exception {
		log.info(" listPage(PageVO vo) ");
		return dao.listPage(vo);
	}
	
	
	
	
	
	
	

}
