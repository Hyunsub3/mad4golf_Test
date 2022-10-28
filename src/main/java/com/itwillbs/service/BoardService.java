package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;

public interface BoardService {

	//글쓰기
	public void boardWrite(BoardVO vo) throws Exception;

	//글 전체 목록
	public List<BoardVO> listBoardAll() throws Exception;

	//글 리스트 가져오기(페이징 처리)
	public List<BoardVO> listPage(PageVO vo) throws Exception;
	
}
