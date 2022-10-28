package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;

public interface BoardDAO {
	
	//글쓰기 - boardWrite(vo)
	public void boardWrite(BoardVO vo) throws Exception;

	//글 전체 목록 - listBoardAll()
	public List<BoardVO> listBoardAll() throws Exception;

	// 글 전체목록 - listPage(page) 
	public List<BoardVO> listPage(Integer page) throws Exception;
	
	// 글 전체 목록 - listPage(pageVO)
	public List<BoardVO> listPage(PageVO vo) throws Exception;
}
