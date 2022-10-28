package com.itwillbs.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;
import com.itwillbs.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService{
	
	public static final Logger log
			= LoggerFactory.getLogger(BoardServiceImpl.class);
	
	
	@Autowired
	private BoardDAO dao;
	
	
	//------------------------------------------------------------------------
	
	
	@Override
	public void boardWrite(BoardVO vo) throws Exception {
		log.info(" 2. service - boardWrite(vo) ");
		
		dao.boardWrite(vo);
	}
	
	
	

	//------------------------------------------------------------------------
	
	
	
	@Override
	public List<BoardVO> listBoardAll() throws Exception {
		log.info(" 2. service - getBoardListAll() ");
		
		List<BoardVO> boardList = dao.listBoardAll();
		
		return boardList;
	}
	

	//------------------------------------------------------------------------
	
	
	
	@Override
	public List<BoardVO> listPage(PageVO vo) throws Exception {
		log.info(" 2. service - listPage(vo) ");
		
		return dao.listPage(vo);
	}
}
