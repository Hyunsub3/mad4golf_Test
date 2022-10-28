package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardDAO {
	
	//글내용 보기
	public BoardVO getBoard(Integer bno) throws Exception;
	//글 조회수 1증가
	public void updateReadCount(Integer bno) throws Exception;
	//글 수정
	public Integer updateBoard(BoardVO vo) throws Exception;
	//글 삭제
	public Integer deleteBoard(Integer bno) throws Exception;
	//목록??
	public List<BoardVO> listAll() throws Exception;

	 
}
