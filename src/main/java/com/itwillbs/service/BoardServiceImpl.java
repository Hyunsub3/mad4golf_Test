package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService{
	
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	//객체 생성
	//BoardDAO 객체 주입(DI)
	@Inject
	private BoardDAO dao;
	
	//글내용
	@Override
	public BoardVO getBoard(Integer board_num) throws Exception {
		return dao.getBoard(board_num);
	}
	
	//조회수
	@Override
	public void updateReadCount(Integer board_num) throws Exception {
		dao.updateReadCount(board_num);
	}
	
	//수정
	@Override
	public Integer updateBoard(BoardVO vo) throws Exception {
		return dao.updateBoard(vo);
	}
	
	//삭제
	@Override
	public Integer deleteBoard(Integer board_num) throws Exception {
		return dao.deleteBoard(board_num);
	}
	
	//목록
	@Override
	public List<BoardVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
