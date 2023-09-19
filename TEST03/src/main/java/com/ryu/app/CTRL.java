package com.ryu.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CTRL {
	
	@RequestMapping("/")
	public @ResponseBody String root() {
		return "루트 요청";
	}
	
	@RequestMapping("/test")
	public String test(VO vo, Model model) {
		System.out.println("vo : " + vo);
		model.addAttribute("apple", vo.getId());
		return "test";
	}
	
	@RequestMapping("/test01")
	public String test01(HttpServletRequest request, Model model) {
		VO vo = new VO();
		System.out.println("vo : " + vo);
		model.addAttribute("apple", request.getParameter("id"));
		return "test";
		// request 자체가 not POJO인 서블릿 객체이기 때문에 무거워서 활용도가 낮다
	}
	
	@RequestMapping("/test02")
	public String test02(@RequestParam("id")String id, @RequestParam("name")String name, Model model) {
		model.addAttribute("apple", id);
		return "test";
		// 가독성이 커맨드 객체를 사용할 때보다 별로이기 때문에 잘 쓰이지 않는다
		// 그래도 의외로 많이 보이는 경우
	}
	
	@RequestMapping("/test03/{id}/{name}") // {} 안에 무엇을 받아올지 적음
	public String test03(@PathVariable String id, @PathVariable String name, Model model) { // @PathVariable >> @RequestParam의 진화 버전이라고 생각하면됨 
		model.addAttribute("apple", id);
		model.addAttribute("banana", name);
		return "test";
		// 모바일용 앱을 따로 만드는 것은 비용이 많이 들기 때문에 웹에서 적용 시키기 위해 이런식으로 구현함
		// REST API
		// 이 방법은 URL에 데이터를 바로 보내는 방법
	}
	
	
	
}
