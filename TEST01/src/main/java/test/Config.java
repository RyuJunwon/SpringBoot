package test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 설정 클래스
// Configuration : 프로그램의 설정을 저장해두고 환경을 구성하는 데에 사용하는 파일

@Configuration // 컨테이너야 이거 설정파일이다 !! 라고 어노테이션을 통해 알려줌
public class Config {

	@Bean  // Bean 어노테이션을 달게 되면 스프링이 IoC 컨테이너로 관리하는 객체가 됨
	public PhoneA phoneA() {
		return new PhoneA();
	}
	
	@Bean
	public PhoneB phoneB() {
		return new PhoneB();
	}
	
	// 멤버를 2가지 방식으로 만들 것임 (생성자 주입, setter 주입)
	public Member member1() {
		// 생성자 주입 방식
		return new Member("큰 사람", new PhoneA());
	}
	
	@Bean(name="apple") // 이름 설정을 별도로 하지 않으면 member1 << 메소드명이 등록됨
	public Member member2() { 
		// setter 주입 방식
		Member member = new Member();
		member.setName("작은 사람");
		member.setPhone(new PhoneA());
		return member;
	}
}
