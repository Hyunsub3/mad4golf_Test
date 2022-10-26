package com.itwillbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

// @ControllerAdvice  : 컨트롤러에서 발생한 예외(Exception)를
// 전문적으로 처리하는 객체라는 의미 => bean등록

@ControllerAdvice
public class CommonExceptionAdvice {
	// 예외를 처리하는 보조기능(객체)
	
	private static final Logger log 
	    = LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
//	@ExceptionHandler(NullPointerException.class)
//	public void test() {
//		log.info("test()");
//	}
	
	/*
	 * @ExceptionHandler(Exception.class) public String common(Exception e,Model
	 * model) { log.info(" common() 호출 "); log.info(e.getMessage());
	 * log.info(e.toString());
	 * 
	 * model.addAttribute("e", e.getMessage());
	 * 
	 * return "commons"; }
	 */
	
	@ExceptionHandler(Exception.class)
	public ModelAndView common(Exception e) {
		log.info(" common(mav) 호출 ");
		log.info(e.getMessage());
		log.info(e.toString());
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/commons");
		mav.addObject("e", e.getMessage());
		
		return mav;
	}
	
	

}
