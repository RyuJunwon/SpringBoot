package com.ryu.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CTRL {
	
	@RequestMapping("/") // 루트 요청
	public @ResponseBody String root() { // 문자열 값 자체를 반환하기 위해 ResponseBody 어노테이션 사용
		return "롬복 예제입니다!";
	}
	
	@RequestMapping("/test") // 루트 요청
	public String test(VO vo, Model model) {
		
		System.out.println("vo : " + vo);
		model.addAttribute("apple", vo);
		return "test";
		// >> /WEB-INF/views/test.jsp 로 감s
	}
	
}
