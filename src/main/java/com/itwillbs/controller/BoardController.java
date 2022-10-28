package com.itwillbs.controller;


import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageMakerVO;
import com.itwillbs.domain.PageVO;
import com.itwillbs.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	
	private static final Logger log 
    = LoggerFactory.getLogger(BoardController.class);

	
	@Inject
	private BoardService service; 
	
	
	
	//--------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------
	
	
	
	//글쓰기 페이지 불러오기
	@RequestMapping(value = "/insertBoard", method = RequestMethod.GET)
	public void insertBoardGET() throws Exception{
		log.info(" 1. controller - insertBoardGET() 호출 ");
		log.info(" /board/insertBoard (get) -> board/insertBoard.jsp");
	}
	
	
	
	
	//--------------------------------------------------------------------------------------------------

	
	
	
	//글 작성해서 DB에 삽입
	@RequestMapping(value = "/insertBoard", method = RequestMethod.POST)
	public String insertBoardPOST(BoardVO vo, RedirectAttributes redirect) throws Exception{
		log.info(" 1. controller - insertBoardPOST() 호출");
		
		//한글 처리
		//전달된 정보 저장
		
		service.boardWrite(vo);
		
		log.info(" 4. controller - 글쓰기 완료 ");
		
		
		
		//페이지 이동(리스트)
//		return "redirect:/board/listAll?msg=writeOk";
		return "redirect:/board/listBoardAll";
	}

	
	
	
	
	//--------------------------------------------------------------------------------------------------
	
	
	
	//게시판 리스트 - 조회(GET)
	@RequestMapping(value = "/listBoardAll", method = RequestMethod.GET)
	public void listBoardAllGET(/* @ModelAttribute("msg") String msg, */Model model, HttpSession session) throws Exception{
		log.info(" 1. controller - listBoardAllGET() 호출 ");
		
		
		List<BoardVO> boardList = service.listBoardAll();
		
		model.addAttribute("boardList", boardList);
		
		//세션 객체 - isUpdate 정보 전달
		session.setAttribute("isUpdate", false);
		
		
	}
	
	
	
	
	//--------------------------------------------------------------------------------------------------

	
	
	
	
	
	//게시판 리스트(페이징 처리) - GET
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public String listPageGET(Model model,PageVO vo) throws Exception{
		log.info(" 1. controller - listPageGET ");
		
//		vo.setPage(2);
//		vo.setPerPageNum(30);
		
		model.addAttribute("boardList", service.listPage(vo));
		
		//페이징 처리 하단부 정보 저장
		PageMakerVO pm = new PageMakerVO();
		pm.setVo(vo);
		pm.setTotalCnt(4999);
		
		model.addAttribute("pm", pm);
		
		return"/board/listAll";
	}
	
	
	
	

	
	//--------------------------------------------------------------------------------------------------
	
	
	
	
	
}
