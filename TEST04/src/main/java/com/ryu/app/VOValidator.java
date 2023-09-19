package com.ryu.app;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class VOValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// 유효성 검사할 객체의 클래스 정보를 반환
		// 따라서 뭘 할 건지를 return 값으로 알려주면 됨
		return VO.class.isAssignableFrom(clazz); // 유효성 검사할 타켓클래스를 보내주고 있구나 !
	}

	@Override
	public void validate(Object target, Errors errors) {
		// 커맨드 객체
		// target : 유효성 검사할 객체(현재는 VO 객체가 들어옴)
		// error : 검증이 통과되지 못한 경우, 왜 통과가 안되었는지를 반환하는 객체
		// ㄴ> 반환을 위해 만들어진 객체 / 몇개의 오류를 반환할지 모르기 때문에 객체로 반환한다
		// 또한, 코드에 직접 new를 작성하는 것은 유지보수에 불리하기 때문에 커맨드 객체로 선언하여 사용한다
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id값 없음"); // 29 - 33 을 대신하는 것이 얘임
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password값 없음");

		VO vo = (VO)target;
		String id = vo.getId();
		
		if(id.length() <= 5) {
			errors.rejectValue("id", "id는 5글자 이하가 될 수 없습니다.");
		}
		
		
	
		
//		if(id == null || id.isEmpty() || id.isBlank() || id.trim().isBlank()) { // trim().isBlank() : 앞 뒤를 잘라서 공백 체크 
//			System.out.println("로그 : id값이 올바르지 않습니다.");
//			errors.rejectValue("id", "id값 없음");
//		}
		
		String password = vo.getPassword();
		
//		if(password == null || password.isEmpty() || password.isBlank() || password.trim().isBlank()) {
//			System.out.println("로그 : password값이 올바르지 않습니다.");
//			errors.rejectValue("password", "password값 없음");
//		}
	}

}
