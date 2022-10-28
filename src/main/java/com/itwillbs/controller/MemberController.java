package com.itwillbs.controller;

import javax.inject.Inject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	// 서비스 객체 필요(의존적)
	@Inject
	private MemberService service;

	// 회원가입 - GET
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insertGET() throws Exception {
		log.info("insertGET() 호출");
		log.info(" /member/sign (get) -> /member/insert.jsp 로 연결");
	}

	// 회원가입 - POST
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertPOST(MemberVO vo, HttpServletRequest request) throws Exception {
		log.info("insertPOST() 호출");
		log.info("@@@@@"+vo);
		int result = service.idCheck(vo);

		try {
			if (result == 1) {
				return "/member/insert";
			} else if (result == 0) {
				log.info(vo + "");
				service.insert(vo);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}

		log.info("@@@@@회원가입 성공! ");

		return "redirect:/member/login";
	}

	// 아이디 중복 체크
	@ResponseBody
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public String idCheck(MemberVO vo) throws Exception {
		log.info("idCheck() 호출");
		log.info("@@@@@@"+vo);
		String result = service.idCheck(vo)+"";
		return result;
	}
	
		// ***** 지연 시작 진행중 *****
	
		// http://localhost:8088/member/login
		// 로그인 - GET
		@RequestMapping(value = "/login",method = RequestMethod.GET )
		public String login() {
			log.info(" login() 실행 ");
			log.info(" 연결된 뷰 페이지로 이동 ");
			
			return "/member/login";
		}
		
		// 로그인 - POST
		@RequestMapping(value = "/login",method = RequestMethod.POST)
		public String login(MemberVO vo,HttpSession session) throws Exception {
			log.info(" loginPOST() 호출 ");
			log.info("@@@@ login : "+vo);
			
			MemberVO loginVO = service.login(vo);
			
			log.info("loginVO : "+loginVO);
			
			// 로그인 여부 확인
			if(loginVO != null) {
				
			session.setAttribute("loginVO", loginVO);
			
			return "redirect:/member/main";
			
			}else {
			// 실패 -> 로그인페이지 이동
			return "redirect:/member/login";
			}
		}
			
			// http://localhost:8088/member/main
			// 메인페이지 -  GET    
			@RequestMapping(value = "/main", method = RequestMethod.GET )
			public String main() {
				log.info(" mainGET() 호출 ");
				log.info(" void 리턴 : /main.jsp 뷰 호출 ");
				
				return "member/main";
			}
			
			
			// 로그아웃 GET/POST
			@RequestMapping(value = "/logout", method = RequestMethod.GET)
			public String logout(HttpSession session) {
				// 로그아웃 -> 세션초기화
				session.invalidate();
				log.info(" 세션 초기화 완료 => 로그아웃 ");
				
				// 페이지 이동	
				return "redirect:/member/main";
			}
			
		}