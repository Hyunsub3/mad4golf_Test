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
import com.itwillbs.service.ProductService;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	// http://localhost:8080/controller/test (x)
	// http://localhost:8080/controller/board/test (o)
	// http://localhost:8080/board/test (o , 서버 모듈 설정후)
	//	@RequestMapping("/test")
	//	public void test() {
	//		log.info("test() 호출");
	//	}
	
	private static final Logger log 
	    = LoggerFactory.getLogger(ProductController.class);
	
	// 서비스객체 필요(의존적)
	@Inject
	private ProductService service;
	
	
	// http://localhost:8080/board/regist
	// 글쓰기 - GET
	@RequestMapping(value = "/regist",method = RequestMethod.GET)
	public void registerGET() throws Exception{
		log.info(" registerGET() 호출 ");
		log.info(" /board/regist (get) -> /board/regist.jsp");
		
	}
	
	// 글쓰기 - POST
	@RequestMapping(value = "/regist",method = RequestMethod.POST)
	public String registerPOST(BoardVO vo,RedirectAttributes rttr /*Model model*/) throws Exception{
		log.info("registerPOST() 호출");
		// 한글처리 (필터)
		// 전달된 정보 저장
		log.info("글쓰기 정보 : "+vo);
		
		// 서비스 - 글쓰기 동작
		service.boardWrite(vo);
		
		log.info(" 글쓰기 완료 !! ");
		
		//model.addAttribute("msg", "OK");
		
		// RedirectAttributes 객체 => rediret 페이지 이동시에만 사용가능
		rttr.addFlashAttribute("msg", "OK");
		// -> 1회성 데이터 (체크용), URL에 표시 x
		
		// 페이지 이동(리스트) 화면,주소 모두 변경
		
		//return "/board/success";
		//return "redirect:/board/listAll?msg=OK";
//		return "redirect:/board/listAll";
		return "redirect:/board/listPage";
	}
	
	
	//http://localhost:8080/board/listAll
	// 게시판 리스트 - 조회 (GET)
	@RequestMapping(value = "/listAll",method = RequestMethod.GET)
	public String listALLGET(ProductVO vo,Model model,HttpSession session) throws Exception {
		log.info("listALLGET() 호출 ");

		
		// 서비스 - 글전체 목록 가져오는 메서드
		List<ProductVO> ProductList = service.getProductListAll(vo);
		
		model.addAttribute("ProductList", ProductList);
		
		// 세션객체 - isUpdate 정보전달
		session.setAttribute("isUpdate", false);
		
		log.info("/Product/shop -> /product/shop.jsp ");
		
		return"/product/shop";
	}
	
	//상품 페이징 리스트(GET) : 페이징처리 완료된 페이지 호출
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public String listPageGET(PageVO vo,Model model) throws Exception{
		log.info("Controller: listPageGET()호출");
		log.info("vo :"+vo);
		
		/*
		 * //PageVO 객체 생성
		 */		/*
		 * PageVO vo = new PageVO(); 
		 * vo.setPage(2); //2페이지에 해당하는 글을
		 * vo.setPerPageNum(30); //30개씩 표시
		 */		
		//View페이지로 vo(글의 정보 : Service -> DAO -> mapper과정으로 담아온 데이터를 View로 보내줌) 
		model.addAttribute("ProductList",service.listPage(vo));
		
		//페이징 처리 하단부 정보 저장   
		PageMakerVO pm = new PageMakerVO();
		pm.setVo(vo); //페이징 처리 하단부 정보를 vo에 받아오고 
		pm.setTotalCnt(512); //calData() 페이징처리에 필요한 계산식 계산 메서드가 포함된 전체 글 갯수 초기화 메서드 호출 
		
		log.info("pm"+pm);
		log.info("vo"+vo);
		
		//model객체에 담아서 view페이지로 페이징 처리 정보 전달 
		model.addAttribute("pm",pm);
		
		return"/product/shop";
	}
	//상품 페이징 리스트(GET) : 페이징처리 완료된 페이지 호출
	
	
	// http://localhost:8080/board/read?bno=12
	
	// 글 본문보기 - GET
	@RequestMapping(value = "/read",method = RequestMethod.GET)
	public void readGET(HttpSession session,@RequestParam("bno") int bno,Model model /* @ModelAttribute("bno") int bno */) throws Exception {
		log.info("readGET() 호출");
		// 전달정보 (bno)
		log.info(" bno : "+bno);
		
		//boolean isUpdate = false;
		log.info(" isUPdate : "+session.getAttribute("isUpdate"));
		
		boolean isUpdate = (boolean)session.getAttribute("isUpdate");
		
		//if(isUpdate == false) {
		if(!isUpdate) {
			// 서비스 - updateReadCount(BNO)
			service.updateReadCount(bno);
			log.info(" 조회수 1증가! ");
			session.setAttribute("isUpdate", true);
		}
		
		// 서비스 - getBoard(int)
		BoardVO vo = service.getBoard(bno);
		
		log.info(vo+"");
		model.addAttribute("vo", vo);
		
		log.info("/board/read -> /board/read.jsp");
	}
	
	// 글 수정하기 - GET (기존의 정보 조회 출력+수정할 정보 입력)
	@RequestMapping(value = "/modify",method = RequestMethod.GET)
	public void modifyGET(@RequestParam("bno")  int bno,Model model) throws Exception{
		// 전달정보 저장(bno)
		log.info("@@@"+bno);
		
		// 서비스 - 게시판 글 정보를 가져오는 메서드
		// 연결된 뷰에 정보 전달(Model객체)
		model.addAttribute("vo", service.getBoard(bno));
		// 페이지 이동(출력) /board/modify
	}
	
	// 글 수정하기 - POST(수정할데이터 처리)
	@RequestMapping(value = "/modify",method = RequestMethod.POST)
	public String modifyPOST(BoardVO vo,RedirectAttributes rttr) throws Exception {
		log.info(" modifyPOST() 호출 ");
		// 한글처리(생략)		
		// 전달정보 저장(수정할 정보) VO
		log.info("@@수정할정보@@"+vo);
		
		// 서비스 - 글 수정메서드
		int cnt = service.updateBoard(vo);
		
		// 수정성공시 /listAll 페이지 이동
		if(cnt == 1) {
			rttr.addFlashAttribute("msg", "MODOK");
			
//			return "redirect:/board/listAll";
			return "redirect:/board/listPage";
		}else {
			// 예외처리 
			// new NullPointerException();
			return "/board/modify?bno="+vo.getBno();
		}
		
	}
	
	// 글 삭제 - POST
	@RequestMapping(value = "/remove",method = RequestMethod.POST )
	public String removePOST(@RequestParam("bno") int bno,RedirectAttributes rttr) throws Exception {
		log.info(" removePOST() 호출 ");
		
		// 전달정보 저장(bno)
		log.info("bno : "+bno);
		
		// 서비스 - 글삭제 동작 (bno)
		int result =service.deleteBoard(bno);
		
		if(result == 1) {
			rttr.addFlashAttribute("msg", "DELOK");
		}
		
		// 글 리스트 페이지 이동		
//		return "redirect:/board/listAll";
		return "redirect:/board/listPage";
	}
	

	
		
		
	
	
	

}

