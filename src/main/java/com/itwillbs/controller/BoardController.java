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
import com.itwillbs.domain.ProductVO;
import com.itwillbs.service.BoardService;
import com.itwillbs.service.ProductService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	// 서비스객체 필요(의존적)
	@Inject
	private BoardService service;

	// 글내용
	// http://localhost:8088/board/boardRead?board_num=1
	@RequestMapping(value = "/boardRead", method = RequestMethod.GET)
	public void boardReadGET(HttpSession session, @RequestParam("board_num") int board_num, Model model)
			throws Exception {
		log.info(" registerGET() 호출 ");
		log.info(board_num + " --------------bno");

		// 세션으로 넘어온 값 확인
		log.info("############################isUpdate : " + session.getAttribute("isUpdate"));
		//boolean isUpdate = (boolean) session.getAttribute("isUpdate");
		//if (!isUpdate) {
			service.updateReadCount(board_num);
		//	log.info("조회수 1증가!!!띠용");
		//	session.setAttribute("isUpdate", true);
		//}

		model.addAttribute("vo", service.getBoard(board_num));
	}
	
	
	//글 수정하기 - GET(기존의 정보 조회 출력 + 수정할 정보 입력)
	@RequestMapping(value="/boardModify", method = RequestMethod.GET)
	public void modifyGET(@RequestParam("board_num") int board_num, Model model) throws Exception{
		
		//연결된 뷰로 전달
		model.addAttribute("vo", service.getBoard(board_num));
		
		//페이지 이동(출력) /board/modify.jsp
	}
	
	//글 수정하기 - POST(수정할 데이터 처리)
	@RequestMapping(value="/boardModify", method = RequestMethod.POST)
	public String modifyPOST(BoardVO vo, RedirectAttributes rttr) throws Exception{
		
		//전달 정보 저장
		log.info("################################" + vo);
		 
		//서비스 - 글 정보 수정
		int cnt = service.updateBoard(vo);
		
		if(cnt == 1) {
			//수정 성공 시 /listAll 페이지 이동
			rttr.addFlashAttribute("msg", "UPDATEOK");
			return "redirect:/board/listPage";
		} else {
			//수정 실패 시
			return "/board/modify?bno="+vo.getBoard_num();
		}
		
	}
	
	//글 삭제하기
	@RequestMapping(value="/boardDelete", method = RequestMethod.GET)
	public String delete(@RequestParam("board_num") int board_num, RedirectAttributes rttr) throws Exception{
		//전달 정보 저장
		
		//서비스 - 글 삭제
		service.deleteBoard(board_num);
		
		log.info("글 삭제 완료");
		rttr.addFlashAttribute("msg", "DELETEOK");
		
		return "redirect:/board/listPage";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//성공하면 가는 곳
	@RequestMapping(value="/listPage", method = RequestMethod.GET)
	public void listAll(HttpSession session) throws Exception{
		
		
		session.setAttribute("isUpdate", false);
		//페이지 이동(출력) /board/listPage.jsp
	}
	
	
	
	



}
