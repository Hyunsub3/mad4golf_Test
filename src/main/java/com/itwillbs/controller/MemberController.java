package com.itwillbs.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

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

	// =========================서하씨 끝, 현섭시작====================================== //
	
	// 가상주소 http://localhost:8080/member/update
		// => /WEB-INF/views/member/update.jsp
		@RequestMapping(value = "/member/update", method = RequestMethod.GET)
		public String update(HttpSession session, Model model) {
			// 세션값 가져오기
			String id=(String)session.getAttribute("loginID");
			// 수정할 정보 가지고 감.
			MemberVO vo = service.getMember(id);
			model.addAttribute("vo",vo);
			
			// 가상주소 변경없이 jsp 이동
		//  /WEB-INF/views/member/update.jsp
			return "member/update";
		}
		
		
		// 가상주소 http://localhost:8080/member/updatePro
		// 수정처리 메시지 "MemberController updatePro()"
		// 가상주소 redirect:/main/main 이동
		@RequestMapping(value = "/member/updatePro", method = RequestMethod.POST)
		public String updatePro(MemberVO vo) {
			System.out.println("MemberController updatePro()");
			// loginMember 비밀번호 일치 여부 확인
			MemberVO vo2 = service.loginMember(vo);
			// 아이디 비밀번호 일치 => 수정처리 => /main/main 이동
			// 아이디 비밀번호  틀림  => msg.jsp  뒤로이동
			if(vo2!=null) {
				// 아이디 비밀번호 일치
				//수정처리
				int result = service.updateMember(vo);
				// 메인 페이지로 이동
				// 주소가 변경되면서 가상주소 이동
//				response.sendRedirect() 
				return "redirect:/main/main";
			}else {
				// 아이디 비밀번호 틀림
			//  /WEB-INF/views/member/msg.jsp
				return "member/msg";
			}
		}
		
		@RequestMapping(value = "/member/list", method = RequestMethod.GET)
		public String list() {
			
			// 가상주소 변경없이 jsp 이동
		//  /WEB-INF/views/member/list.jsp
			return "member/list";
		}
	
	
	
	
}

