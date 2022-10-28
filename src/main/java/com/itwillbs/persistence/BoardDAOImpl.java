package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	
	//SqlSession객체(디비연결, mybatis, 매퍼, 자원해제)
	@Inject
	private SqlSession sqlSession;
	
	//boardMapper 가상이름 정의
	private static final String NAMESPACE = "com.itwillbs.mapper.BoardMapper";
	
	
	/////////////////////////////////////////////////////////////////////////////
	
	//글내용
	@Override
	public BoardVO getBoard(Integer board_num) throws Exception {
		return sqlSession.selectOne(NAMESPACE+".getBoard", board_num);
	}
	
	//조회수
	@Override
	public void updateReadCount(Integer board_num) throws Exception {
		sqlSession.update(NAMESPACE+".updateReadCount", board_num);
	}
	
	//수정
	@Override
	public Integer updateBoard(BoardVO vo) throws Exception {
		return sqlSession.update(NAMESPACE+".updateBoard", vo);
	}
	
	//삭제
	@Override
	public Integer deleteBoard(Integer board_num) throws Exception {
		return sqlSession.delete(NAMESPACE+".deleteBoard", board_num);
	}
	
	//목록
	@Override
	public List<BoardVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
