package com.ryu.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
public class CTRL {

	@RequestMapping("/")
	public String root() {
		return "test";
	}

	@RequestMapping("test")
	public String test(@Valid VO vo, BindingResult br, Model model) {
		// BindingResult 객체는 Validator의 Error 결과값을 받아오는 커맨드 객체이다

		// voV.validate(vo, br); // 검사할 객체: vo, 결과값: br // br은 커맨드 객체(참조 변수)

		if(br.hasErrors()) {
			System.out.println("발생한 에러 목록");
			System.out.println(br.getAllErrors());

			if(br.getFieldError("id") != null) { // id에서 Error가 발생했다는 의미
				//System.out.println(br.getFieldError("id").getCode());
				System.out.println(br.getFieldError("id").getDefaultMessage()); // 어노테이션으로 설정한 값 볼게 !
			}
			if(br.getFieldError("password") != null) { // pw에서 Error가 발생했다는 의미
//				System.out.println(br.getFieldError("password").getCode());
				System.out.println(br.getFieldError("password").getDefaultMessage());
			}

		}

		model.addAttribute("apple", vo.getId());
		return "test";
	}
	// 컨트롤러가 동작했을 때 Validator가 이미 new 되있을 수 있도록 메소드를 생성한다
	@InitBinder
	protected void initBinder(WebDataBinder wdb) {
		wdb.setValidator(new VOValidator());
	}
}
